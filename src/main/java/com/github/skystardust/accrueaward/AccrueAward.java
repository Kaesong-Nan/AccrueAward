package com.github.skystardust.accrueaward;

import com.github.skystardust.accrueaward.Command.GeneralCommand;
import com.github.skystardust.accrueaward.Database.DatabaseManager;
import com.github.skystardust.accrueaward.Listener.PlayerPointListener;
import com.github.skystardust.accrueaward.SettingManager.ConfigGetter;
import com.github.skystardust.accrueaward.SettingManager.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public final class AccrueAward extends JavaPlugin implements Listener {
    private String prefix = ChatColor.GREEN+"§l[累计充值]";
    @Override
    public void onEnable() {
        // Plugin startup logic
        ConfigLoader.getInstance().setupConfigLoader(this);
        File data = new File(getDataFolder(),"playerdata");
        if (!data.exists()){
            data.mkdirs();
        }
        saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new PlayerPointListener(), this);
        getServer().getPluginCommand("aw").setExecutor(new GeneralCommand());
        if (ConfigGetter.getInstance().isDatabase()){
            DatabaseManager.getInstance().init();
            DatabaseManager.getInstance().openConnection();
        }
        if (!Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")){
            getServer().getConsoleSender().sendMessage(prefix+" 插件未找到前置插件PlayerPoints,插件关闭");
            setEnabled(false);
        }else {
            getServer().getConsoleSender().sendMessage(prefix+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            getServer().getConsoleSender().sendMessage(prefix+">插件开启成功!");
            getServer().getConsoleSender().sendMessage(prefix+">作者:星燚");
            getServer().getConsoleSender().sendMessage(prefix+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(prefix+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        getServer().getConsoleSender().sendMessage(prefix+">插件关闭成功!");
        getServer().getConsoleSender().sendMessage(prefix+">作者:星燚");
        getServer().getConsoleSender().sendMessage(prefix+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
