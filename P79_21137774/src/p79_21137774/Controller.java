/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

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
                break;
            case "Sign up":
                username = this.view.usernameInput.getText();
                password = this.view.passwordInput.getText();
                this.model.signUp(username, password);
                break;
            case "Start!":
                this.model.betting(Integer.parseInt(this.view.betInput.getText()));                
                break;
            case "Draw":
                if(this.model.ph.hand.size() < 5)
                {
                    this.model.draw();
                } 
                
                else 
                {
                    this.view.message.setText("You can't have more than 5 cards! Please press Stand.");
                }               
                break;
            case "Stand":
                break;
            case "Play Again":              
                break;
            case "Quit":
                this.model.quitGame();
                break;
            case "Restart":
                this.model = new Model();
                this.view = new View();
                break;
                
            default:
                break;
        }
    }
    
}
