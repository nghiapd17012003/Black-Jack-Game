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
        switch (command)
        {
            case "Log in":
                break;
            case "Sign up":
                break;
            case "Start!":
                break;
            case "Draw":
                break;
            case "Stand":
                break;
            case "Play Again":
                break;
            case "Quit":
                break;
            case "Restart":
                break;
        }
    }
    
}
