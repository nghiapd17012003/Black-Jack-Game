/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

import java.util.HashSet;

/**
 *
 * @author nghia
 */
public class DealerHand extends Hand implements Draw{
    
    DealerHand()
    {
        super.hand = new HashSet<>();
    }
    
    @Override
    public void showHand()
    {
        for(Card c : hand)
        {
            System.out.println(c);
        }
    }

    @Override
    public int handValue() {
        int value = 0;
        
        for (Card c : super.hand) {
            value += c.get_Rank().get_cardRank();
        }
        
        return value;
    }

    @Override
    public void draw(Card c) {
        if(handValue() < 17)//make sure dealer cant draw more if the hand hit 17 or more. 
        {
            super.hand.add(c);
        }
    }
}
