package com.github.skystardust.accrueaward.SettingManager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ConfigGetter
{
    private static ConfigGetter instance = new ConfigGetter();
    private boolean isDatabase;
    private String DatabaseName;
    private String TableName;
    private String DatabaseHost;
    private String DatabasePort;
    private String DatabaseUser;
    private String DatabasePassword;
    private List<String> AwardList;
    private FileConfiguration config=ConfigLoader.getInstance().getConfig();
    private HashMap<Integer,List<String>> commandlist = new HashMap<>();

    public static ConfigGetter getInstance(){
        return instance;
    }
    public boolean isDatabase() {
        isDatabase = ConfigLoader.getInstance().isDatabase();
        return isDatabase;
    }

    public String getDatabaseName() {
        DatabaseName = config.getString("Database.database");
        return DatabaseName;
    }

    public String getTableName() {
        TableName = "accrue";
        return TableName;
    }

    public String getDatabaseHost() {
        DatabaseHost = config.getString("Database.ip");
        return DatabaseHost;
    }

    public String getDatabasePort() {
        DatabasePort = config.getString("Database.port");
        return DatabasePort;
    }

    public String  getDatabaseUser() {
        DatabaseUser = config.getString("Database.username");
        return DatabaseUser;
    }

    public String getDatabasePassword() {
        DatabasePassword = config.getString("Database.password");
        return DatabasePassword;
    }
    public int getVarintByPlayer(Player player) throws IOException {
        Plugin plugin = ConfigLoader.getInstance().getPlugin();
        File playerconfig = new File(plugin.getDataFolder(),"playerdata/"+player.getName()+".yml");
        boolean wascreate = false;
        if (!playerconfig.exists()){
            playerconfig.createNewFile();
            wascreate = true;
        }
        FileConfiguration yaml = YamlConfiguration.loadConfiguration(playerconfig);
        if (wascreate){
            yaml.addDefault("Value",0);
        }
        int var = yaml.getInt("Value");
        return var;
    }
    public int getVarintByPlayer(String playername) throws IOException {
        Plugin plugin=ConfigLoader.getInstance().getPlugin();
        File playerconfig = new File(plugin.getDataFolder(),"playerdata/"+playername+".yml");
        boolean wascreate = false;
        if (!playerconfig.exists()){
            playerconfig.createNewFile();
            wascreate = true;
        }
        FileConfiguration yaml = YamlConfiguration.loadConfiguration(playerconfig);
        if (wascreate){
            yaml.addDefault("Value",0);
        }
        int var = yaml.getInt("Value");
        return var;
    }

    public List<String> getAccrueCommandList(int value){
        return commandlist.get(value);
    }

    public List<String> getAwardList() {
        ArrayList<String> list = new ArrayList();
        Iterator<String> iterator = config.getConfigurationSection("Accrue").getKeys(false).iterator();
        iterator.forEachRemaining(awardkey ->{
            list.add(awardkey);
            ArrayList<String> index = new ArrayList<String>();
            index.addAll(config.getStringList("Accrue."+awardkey+".runcommand"));
            commandlist.put(Integer.valueOf(awardkey),index);
        });
        AwardList = list;
        return AwardList;
    }
    public boolean isPlayerGotAward(Player player,int awardamount){
        File datafile = new File(ConfigLoader.getInstance().getPlugin().getDataFolder(),"playerdata/"+player.getName()+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(datafile);
        return data.getBoolean(String.valueOf(awardamount));
    }
    public boolean isPlayerGotAward(String playername,int awardamount){
        File datafile = new File(ConfigLoader.getInstance().getPlugin().getDataFolder(),"playerdata/"+playername+".yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(datafile);
        return data.getBoolean(String.valueOf(awardamount));
    }
}
