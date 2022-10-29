/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nghia
 */
public class Controller implements ActionListener{
    
    public View view;
    public Model model;
    
    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);       
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();//method will give us String repersentaion in action commnad. The value will be component specific
        String username;
        String password;
        switch (command)
        {
            case "Log in":
                username = this.view.usernameInput.getText();
                password = this.view.passwordInput.getText();
                this.model.logIn(username, password);
                if(this.model.data.loginFlag)
                {
                    this.view.preGamePhase(this.model.data.balance);
                }               
                break;
            case "Sign up":
                username = this.view.usernameInput.getText();
                password = this.view.passwordInput.getText();
                this.model.signUp(username, password);
                
                this.view.preGamePhase(this.model.data.balance);
                if(this.model.data.loginFlag)
                {
                    this.view.preGamePhase(this.model.data.balance);
                }              
                               
                break;
            case "Start!":
                int betAmount = Integer.parseInt(this.view.betInput.getText());
                boolean bettingFlag = this.model.betting(betAmount); 
                if(bettingFlag == false)
                {
                    this.view.message.setText("Your betting should be bigger than 0 and not more than your balance!");
                }
                
                else
                {
                    String betAmountInString = Integer.toString(betAmount);
                    this.view.game(betAmountInString);
                }
                
                break;
            case "Draw":
                if(this.model.ph.hand.size() < 5)
                {
                    Card c = model.getCardFromDeck();
                    this.model.draw(c);
                    CardImage cardImage = new CardImage();
                    cardImage.c = c;
                    this.view.add(cardImage);
                } 
                
                else 
                {
                    this.view.message.setText("You can't have more than 5 cards! Please press Stand.");
                }               
                break;
            case "Stand":
                this.model.compareHand();
                this.model.updateBalance();
                this.view.afterGamePhase(this.model.data.balance);
                break;
            case "Play Again":
                this.model.playAgain();
                break;
            case "Quit":
                this.model.quitGame();
                break;
            case "Restart":
                this.model.restartGame();
                break;               
            default:
                break;
        }
    }
    
}
