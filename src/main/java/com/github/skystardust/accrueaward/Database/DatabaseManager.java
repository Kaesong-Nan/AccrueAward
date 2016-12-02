package com.github.skystardust.accrueaward.Database;

import com.github.skystardust.accrueaward.SettingManager.ConfigGetter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance = new DatabaseManager();

    private String url;
    private String ip;
    private String port;
    private String database;
    private String tablename;
    private String username;
    private String password;

    private Connection connection;

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    public void init(){
        ip = ConfigGetter.getInstance().getDatabaseHost();
        port = ConfigGetter.getInstance().getDatabasePort();
        database = ConfigGetter.getInstance().getDatabaseName();
        tablename = ConfigGetter.getInstance().getTableName();
        username = ConfigGetter.getInstance().getDatabaseUser();
        password = ConfigGetter.getInstance().getDatabasePassword();
    }
    public boolean openConnection(){
        url = "jdbc:mysql://"+ip+":"+port+"/"+database;
        try {
            connection = DriverManager.getConnection(url,username,password);
            Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE+"数据库连接成功!");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"数据库连接失败!");
            return false;
        }
        return true;
    }
}
