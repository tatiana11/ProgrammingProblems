package digitpermutation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Tati
 * @date 02/01/2016
 */
public class DigitPermutation
{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    {
        DigitPermutation dp = new DigitPermutation();
        dp.run();
    }
    
    void run() throws IOException
    {
        Scanner in = new Scanner(new FileReader(new File("digitpermutation.in.txt")));
        int T = in.nextInt();
        in.nextLine();
        
        HashMap<Character,Integer> hm;
        String data;
        String fields[];
        float N;
        char c;
        int value;
        int numZeros;
        float repeats;
        float zeros;
        
        while(T--!=0)
        {
            hm=new HashMap<>();
            numZeros=0;
            repeats=1;
            zeros=1;
            data = in.nextLine();
            fields = data.split(" ");
            
            N=Integer.parseInt(fields[0]);
            data = fields[1];
            
            for(int i=0;i<N;i++)
            {
                c=data.charAt(i);
                if(!hm.containsKey(c))
                    hm.put(c, 1);
                else
                {
                    value = hm.get(c);
                    hm.replace(c, ++value);
                }
            }
            
            //System.out.println(hm.entrySet());
            
            for(Character key: hm.keySet())
            {
                if(key=='0')
                    numZeros = hm.get(key);
                else
                    repeats *= factorial(hm.get(key));
            }
            
            //System.out.println("#0's: " +numZeros);
            
            if(numZeros>0)
            {
                //System.out.println(factorial(N-1)+" "+factorial(numZeros-1)+ " " +repeats);
                zeros = factorial(N-1)/(factorial(numZeros-1)*repeats);
                repeats=factorial(N)/(factorial(numZeros)*repeats);
                System.out.println(repeats-zeros);
            }
            else
            {
                repeats=factorial(N)/repeats;
                System.out.println(repeats);
            }
            
        }
    }
    
    float factorial(float n)
    {
        if(n==0)
            return 1;
        
        return n*factorial(n-1);
    }
}
