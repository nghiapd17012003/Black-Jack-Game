/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

import java.util.Observable;
import java.util.Scanner;

/**
 *
 * @author nghia
 */
public class Model extends Observable {
    public DBManager db;
    public PlayerData data;
    public String username;
    public int betAmount;
    public PlayerHand ph;
    public DealerHand dh;
    public int result;
    private Deck deck;
    
    
    public Model()
    {
        this.db = new DBManager();
        this.db.establishConnection();
        this.ph = new PlayerHand();
        this.dh = new DealerHand();       
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
    
    public boolean betting(int betAmount)
    {
        boolean bettingFlag = false;
        if(0< betAmount && betAmount < data.balance)
        {
            this.betAmount = betAmount;
            bettingFlag = true;
        }        
        
        return bettingFlag;
    }
    
    public void gameStart()
    {
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < 2; i++)
        {
            ph.draw(getCardFromDeck());
        }
        
        //Show player hand
        ph.showHand();
        System.out.println("Player current hand value: " + ph.handValue());       
    }
    
    public int draw(Card c) // for the draw button
    {
        ph.draw(c);
        ph.showHand();
        System.out.println("Your current hand value is : " + ph.handValue());
        
        return ph.handValue();
    }
    
    
    public Card getCardFromDeck()
    {
        Card card = this.deck.newCardFromDeck();
        this.deck.updateDeck(card);
        
        return card;
    }
    
    public int compareHand()
    {
        int result = 0;
        //1 : player win
        //-1: dealer win
        //0 : draw
        if(dh.handValue() <= 21 && ph.handValue() <= 21)
        {
            if(dh.handValue() > ph.handValue())
            {
                result = -1;
            }
            
            else if(dh.handValue() < ph.handValue())
            {
                result = 1;
            }
        }
        
        else if(dh.handValue() <= 21 && ph.handValue() > 21)
        {
            result = -1;
        }
        
        else if(dh.handValue() > 21 && ph.handValue() <= 21)
        {
            result = 1;
        }
        
        else
        {
            result = 0;
        }
        
        return result;
    }
    
    public void updateBalance()
    {
        switch (result) {
            case 1:
                System.out.println("Player win!");
                this.data.balance += this.betAmount;
                break;
            case -1:
                System.out.println("Player lose!");
                this.data.balance -= this.betAmount;
                break;
            default:
                System.out.println("Draw!");                
                break;
        }    
        
        this.setChanged();
    }
    
    public void quitGame()
    {
        this.db.quitGame(this.username, this.data.balance);
        this.data.quitFlag = true;
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void restartGame()
    {
        this.data.restartFlag = true;
        this.db.quitGame(username, this.data.balance);        
    }
    
    public void playAgain()
    {
        this.db.quitGame(username, this.data.balance);
        this.logIn(username, this.db.password_in_database);
    }       
}
