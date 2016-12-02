package com.github.skystardust.accrueaward.Command;

import com.github.skystardust.accrueaward.SettingManager.ConfigGetter;
import com.github.skystardust.accrueaward.SettingManager.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

/**
 * Created by SkyStardust on 2016/10/24.
 */
public class GeneralCommand implements CommandExecutor
{
    private String prefix = ChatColor.AQUA+"[累计充值]";
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length==0){
            commandSender.sendMessage(prefix+"/aw reload 重载插件");
            commandSender.sendMessage(prefix+"/aw check 玩家名字->查询玩家累计充值数额");
            return true;
        }
        if (strings.length==1){
            if (strings[0].equalsIgnoreCase("reload")&&commandSender.hasPermission("aw.reload")){
                ConfigLoader.getInstance().reloadConfig();
                commandSender.sendMessage(prefix+"配置文件重载成功!");
                return true;
            }else {
                commandSender.sendMessage(prefix+"未知参数!");
            }
            return true;
        }
        if (strings.length==2){
            if (strings[0].equalsIgnoreCase("check")){
                if (Bukkit.getPlayer(strings[1])!=null&&commandSender.hasPermission("aw.check")){
                    commandSender.sendMessage(ChatColor.GOLD +"§l===============================");
                    commandSender.sendMessage(ChatColor.GOLD+"§l[累计充值]您查询的玩家:"+strings[1]);
                    try {
                        commandSender.sendMessage(ChatColor.RED+"§l累计充值数额为:"+ ConfigGetter.getInstance().getVarintByPlayer(Bukkit.getPlayer(strings[1])));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    commandSender.sendMessage(ChatColor.GOLD +"§l===============================");
                }else {
                    commandSender.sendMessage(prefix+"玩家未在线!");
                }
                return true;
            }else {
                commandSender.sendMessage(prefix+"未知参数!");
            }
            return true;
        }
        return true;
    }
}
