/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

import java.util.Scanner;
/**
 *
 * @author nghia
 */
public class Game {
    
    private final PlayerHand ph;
    private final DealerHand dh;
    private final Player player;
    
    //constructor
    Game(Player p)
    {
        this.ph = new PlayerHand();
        this.dh = new DealerHand();
        this.player = p;
    }
    
    //Create a game
    public void gameStart()
    {
        Scanner scanner = new Scanner(System.in);
        Card tempc;
        //initialize the game
        Deck deck = new Deck();    
        deck.createDeck();
        
        tempc = deck.newCardFromDeck();
        ph.draw(tempc);
        deck.updateDeck(tempc);
        tempc = deck.newCardFromDeck();
        ph.draw(tempc);
        deck.updateDeck(tempc);
        tempc = deck.newCardFromDeck();
        dh.draw(tempc);
        deck.updateDeck(tempc);
        tempc = deck.newCardFromDeck();
        dh.draw(tempc);
        deck.updateDeck(tempc);
        
        //betting
        int betting = betting();
        System.out.println("You are betting $ " + betting + "\n");
        
        //Show player hand
        ph.showHand();
        System.out.println("Your current hand value is : " + ph.handValue());
        
        //Player's phase
        //I dont make the quit option during player's phase because take advantage of that option to not to lose the money when ever they get a bad hand. 
        while(ph.handValue()< 21)
        {
            System.out.println("Press 1 for stand, 2 for draw");
            String action = scanner.nextLine();
            boolean status = true;
            switch(action)
            {
               case "1":
                   status = false;
                   break;
               case "2":
                   tempc = deck.newCardFromDeck();
                   ph.draw(tempc);
                   deck.updateDeck(tempc); 
                   System.out.println("\n");
                   ph.showHand();
                   System.out.println("Your current hand value is : " + ph.handValue());
                   break;
               default:
                   System.out.println("There is no such action! Please choose again!");
                   break;         
            }
            
            if(status == false)
            {
                break;
            }
            
            //break when player hand have 5 card: Prevent player draw all the card
            if(ph.hand.size() == 5)
            {
                break;
            }  
        }
        
        //Dealer's phase
        System.out.println("\nDealer Hand:");
        while(dh.handValue() < 17)
        {
            tempc = deck.newCardFromDeck();
            dh.draw(tempc);
            deck.updateDeck(tempc);
        }
        
        dh.showHand();
        
        System.out.println("Dealer hand value is: " + dh.handValue());
        //compare hand
        switch (compareHand()) {
            case 1:
                System.out.println("You win!");
                player.setBalance(player.getBalance() + betting);
                break;
            case -1:
                System.out.println("You lose!");
                player.setBalance(player.getBalance() - betting);
                break;
            default:
                System.out.println("Draw!");
                player.setBalance(player.getBalance());
                break;
        }          
    }
    
    //betting 
    public int betting()
    {
        Scanner scanner = new Scanner(System.in);
        String betAmount;
        System.out.println("How much do you wanna bet?");
        do
        {
            betAmount = scanner.nextLine();
        }while(this.isValidInput(betAmount) == false);
        
        while(Integer.parseInt(betAmount) > player.getBalance() || Integer.parseInt(betAmount) < 0)
        {
            if(Integer.parseInt(betAmount) > player.getBalance())
            {
                System.out.println("You cant bet more than your current balance! Please enter your bet amount again!");
            }
            
            else
            {
                System.out.println("You cant bet 0$ or less than that! Please enter your bet amount again!");
            }
            
            do
            {
                betAmount = scanner.nextLine();
            }while(this.isValidInput(betAmount) == false);
        }
        
        return Integer.parseInt(betAmount);
    }
    
    //deciding the winner
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
    
    //check if the input is valid
    public boolean isValidInput(String input)
    {
        try
        {
            Integer.parseInt(input.trim());
        }
        
        catch (NumberFormatException e) 
        {
            System.out.println("Invalid Input. \n Please input your answer again: ");
            return false;
        }
        
        return true;
    }
}
