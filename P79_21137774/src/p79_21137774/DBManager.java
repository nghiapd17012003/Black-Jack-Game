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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:BlackJackDB_Ebd; create = true";
    
    Connection conn;
    
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
    
}
