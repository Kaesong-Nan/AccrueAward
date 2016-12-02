package com.github.skystardust.accrueaward.SettingManager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigLoader
{
    private static ConfigLoader instance = new ConfigLoader();
    private Plugin plugin;
    private FileConfiguration Config;
    private boolean isDatabase;
    private ConfigLoader(){};
    public static ConfigLoader getInstance(){
        return instance;
    }
    public void setupConfigLoader(Plugin plugin){
        this.plugin=plugin;
        Config=loadConfig();
    }
    private FileConfiguration loadConfig(){
        File config = new File(plugin.getDataFolder(),"config.yml");
        if (!config.exists()){
            plugin.saveDefaultConfig();
        }
        FileConfiguration yaml = YamlConfiguration.loadConfiguration(config);
        return yaml;
    }

    public boolean isDatabase() {
        isDatabase = Config.getBoolean("Database.Enable");
        return isDatabase;
    }

    public void reloadConfig(){
        loadConfig();
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public FileConfiguration getConfig() {
        return Config;
    }

}
