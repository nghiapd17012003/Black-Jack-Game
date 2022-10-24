/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

import java.util.Observable;

/**
 *
 * @author nghia
 */
public class Model extends Observable {
    public DBManager db;
    public PlayerData data;
    public String username;
    public int betAmount;
    
    public Model()
    {
        this.db = new DBManager();
        this.db.establishConnection();
    }
    
    public void logIn(String username, String password)
    {
        this.username = username;
        
        this.data = this.db.logIn(username, password);     
        this.setChanged();
        this.notifyObservers(this.data); 
    }
    
    public void signUp(String username, String password)
    {
        this.username = username;
        this.data = this.db.signUp(username, password);  
        this.setChanged();
        this.notifyObservers(this.data); 
    }
    
    public void betting(int betAmount)
    {
        this.betAmount = betAmount;        
    }
    
    public void quitGame()
    {
        this.db.quitGame(this.username, this.data.balance);
        this.data.quitFlag = true;
        this.setChanged();
        this.notifyObservers(this.data);
    }
}
