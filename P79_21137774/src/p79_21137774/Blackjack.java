/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package p79_21137774;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author nghia
 */
public class Blackjack {

    /**
     * @param args the command line arguments
     */
    private final HashMap<String, Player> playerMap;//playerName will be key to find Player
    private final String fileName;
    Blackjack()
    {
        this.playerMap = new HashMap();
        this.fileName = "./resources/playerInfo.txt";
    }
    
    //main
    public static void main(String[] args) {  

        Blackjack blackjack = new Blackjack();  
        Scanner scanner = new Scanner (System.in);
        System.out.println("Enter your name: ");
        String playerName = scanner.nextLine().trim(); 
        blackjack.getPlayer(blackjack.fileName);     
        Player p = blackjack.checkPlayer(playerName);
       
        String option;
        while(true)
        {
            Game newGame = new Game(p);
            newGame.gameStart();
            System.out.println(p.getName() + " , your current balance is: "  + p.getBalance());           
            if(p.getBalance() == 0)
            {
                System.out.println("Your balance is $0 now so you only can play for no money. Maybe go back next time and we might have a litter surpise for you! \nDo you want to continue (Just bet $0 if you continue!)? Type 'X' for exit and any other character to continue!");      
            }
            else
            {
                 System.out.println("Continue? Type 'X' for exit and anyother character to continue!");
            }
            option = scanner.nextLine();
            if(option.compareToIgnoreCase("x") == 0)
            {
                break;
            }
        }
        
        blackjack.updatePlayerInfo(p);       
    }
    
    //get all player's information from a file and store them in the HashMap playerMap
    public void getPlayer(String fn) {
        FileInputStream fin;
        try {
            fin = new FileInputStream(fn);
            Scanner fileScanner = new Scanner(fin);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                StringTokenizer st = new StringTokenizer(line);
                Player p = new Player(st.nextToken(), Integer.parseInt(st.nextToken()));
                this.playerMap.put(p.getName(), p);
            }
            fin.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Check if the player exist
    public Player checkPlayer(String name)
    {
        Player p;
        
        if(this.playerMap.containsKey(name) == true)
        {
            p = this.playerMap.get(name);
            System.out.println("Wellcome back " + name + " ! Your current ballance is " + p.getBalance());
            
            if(p.getBalance() == 0)
            {
                System.out.println("Oh no you are broke... Don't worry, we just gave you $10. Good luck!");
                p.setBalance(10);
                System.out.println("Your current ballance is " + p.getBalance());
            }
        }
        
        else
        {
            p = new Player(name, 100);
            System.out.println("Hello " + name + " ! We just add $100 to your balance!!!");         
            System.out.println("Maybe comeback next time and we might have a surprise for you!");
        }
        
        return p;
    }
    
    //update the file storing all player's information
    public void updatePlayerInfo(Player player) {
        this.playerMap.put(player.getName(), player);
        try {
            FileOutputStream fOut = new FileOutputStream(fileName);
            PrintWriter pw = new PrintWriter(fOut);
            for (Player p : this.playerMap.values()) {
                pw.println(p.getName() + " " + p.getBalance());
            }
            pw.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }      
    }
}
