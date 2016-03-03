/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckofcards;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Tati
 */
public class DeckofCards
{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    {
        DeckofCards dc = new DeckofCards();
        dc.run();
       
    }
    
    void run() throws IOException
    {
        Scanner in = new Scanner( new FileReader(new File("deckofcards.in.txt")));
        int deck[];
        int dealtCards[];
        int a;
        
        int cardHand;
        int player;
        
        int n = in.nextInt();
        int k = in.nextInt();
        
        while(n!=0 && k!=0)
        {
            deck = new int[n+1];
            dealtCards = new int[n+1];
            a=n%k;
            cardHand=-1;
            player=0;
            
            for(int i= 1; i<n+1;i++)
            {
                deck[i]=i;
            }
            
            for(int i=1; i<n+1; i++)
            {
                if(cardHand==-1)
                {
                    if(a==0) cardHand=n/k-1;
                    else
                    {
                        cardHand=n/k;
                        a--;
                    }
                    player++;
                }
                //System.out.println(cardHand+" "+player+" "+k+" "+a);
                dealtCards[i] = cardHand*k+player;
                cardHand--;
            }
            
            int findCard;
            int LCM;
            int LCMs=1;
            
            for(int i=1;i<n+1;i++)
            {
                findCard=i;
                LCM=0; 
                while(deck[i]!=0)
                {
                    for(int j=1;j<n+1;j++)
                    {
                        if(dealtCards[j]==findCard)
                        {
                            LCM++;
                            findCard = deck[j];
                            deck[j]=0;
                            break;
                        }
                    }
                }
                
                if(LCM!=0)
                    LCMs=lcm(LCMs,LCM);
            }
            
            System.out.println(LCMs);
            //System.out.println(Arrays.toString(deck));
            //System.out.println(Arrays.toString(dealtCards));
            
            n=in.nextInt();
            k=in.nextInt();
        }
        
    }
    
    private static int lcm(int a, int b)
    {
        return a * (b / gcd(a, b));
    }
    
    private static int gcd(int a, int b)
    {
        while (b > 0)
        {
            int temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }
    
}
