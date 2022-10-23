/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

/**
 *
 * @author nghia
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:BlackJackDB_Ebd; create = true";

    
    Connection conn = null;
    
    public DBManager()
    {
        establishConnection();
    }
    
    public Connection getConnection()
    {
        return this.conn;
    }
    
    public void establishConnection()
    {
        if(conn != null)
        {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " Get Connect Successfully!");              
                Statement statement = conn.createStatement();
                String tableName = "PlayerInformation";
                
                if(!checkTableExisting(tableName))
                {
                    statement.executeUpdate("CREATE TABLE " + tableName + " (username VARCHAR(12) primary key, password VARCHAR(12), balance INT)"); //username will be the primarykey
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void closeConnection()
    {
        if(conn != null)
        {
            try {
                conn.close();
                System.out.println(URL + " Close Successfully");
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {

            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
            
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + "  is there");
                    flag = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
        }
        return flag;
    }
    
    public PlayerData checkUsername(String username, String password) 
    {
        PlayerData data = new PlayerData();
        
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT username, password, balance FROM PlayerInformation"+ "WHERE username = '"+ username +"'");
            if(rs.next())// if the player is exist
            {  
                String password_in_database = rs.getString(password);
                System.out.println("Found user " + "username: " + rs.getString(username) + "password: " + password_in_database);
                
                if(password_in_database.compareTo(password) == 0) // if the password is correct also
                {
                    data.balance = rs.getInt("balance");
                    data.loginFlag = true;
                }
            }
            
            else
            {
                System.out.println("Player doesnt exit");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (data.loginFlag == false)
        {
            data = null;
        }
        return data;
    }
    
    public PlayerData signUp(String username, String password)
    {
        PlayerData data = new PlayerData();
        
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO PlayerInformation VALUES('" + username + "', '" + password + "', 0)");
            System.out.println("Sign up success!");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
}
