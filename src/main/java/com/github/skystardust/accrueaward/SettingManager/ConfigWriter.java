package com.github.skystardust.accrueaward.SettingManager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigWriter
{
    private static ConfigWriter Instance = new ConfigWriter();
    private FileConfiguration config =  ConfigLoader.getInstance().getConfig();
    private Plugin plugin = ConfigLoader.getInstance().getPlugin();

    public static ConfigWriter getInstance() {
        return Instance;
    }
    private ConfigWriter(){}

    public void resetPlayerValue(Player player) throws IOException {
        File datafile = new File(plugin.getDataFolder(),"playerdata/"+player.getName()+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(datafile);
        data.set("Value",0);
        data.save(datafile);
    }
    public void setPlayerValue(Player player,int val) throws IOException {
        File datafile = new File(plugin.getDataFolder(),"playerdata/"+player.getName()+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(datafile);
        data.set("Value",val);
        data.save(datafile);
    }
    public void addPlayerValue(Player player,int val) throws IOException {
        File datafile = new File(plugin.getDataFolder(),"playerdata/"+player.getName()+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(datafile);
        int def = data.getInt("Value");
        data.set("Value",def+val);
        data.save(datafile);
    }
    public void setPlayerAwarded(Player player,String val,boolean b) throws IOException {
        File datafile = new File(plugin.getDataFolder(),"playerdata/"+player.getName()+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(datafile);
        data.set(String.valueOf(val),b);
        data.save(datafile);
    }
    public void resetPlayerValue(String playername) throws IOException {
        File datafile = new File(plugin.getDataFolder(),"playerdata/"+playername+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(datafile);
        data.set("Value",0);
        data.save(datafile);
    }
    public void setPlayerValue(String playername,int val) throws IOException {
        File datafile = new File(plugin.getDataFolder(),"playerdata/"+playername+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(datafile);
        data.set("Value",val);
        data.save(datafile);
    }
    public void addPlayerValue(String playername,int val) throws IOException {
        File datafile = new File(plugin.getDataFolder(),"playerdata/"+playername+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(datafile);
        int def = data.getInt("Value");
        data.set("Value",def+val);
        data.save(datafile);
    }
    public void setPlayerAwarded(String playername,String val,boolean b) throws IOException {
        File datafile = new File(plugin.getDataFolder(),"playerdata/"+playername+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(datafile);
        data.set(String.valueOf(val),b);
        data.save(datafile);
    }

}
