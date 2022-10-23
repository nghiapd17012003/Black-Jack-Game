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
    
    public Model()
    {
        this.db = new DBManager();
        this.db.establishConnection();
    }
}
