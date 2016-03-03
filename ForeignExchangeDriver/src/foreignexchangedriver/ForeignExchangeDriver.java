package foreignexchangedriver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author Tatiana
 */
public class ForeignExchangeDriver {
    public Map<Integer,List<ForeignExchange>> hm;
    public String file_path;
    
    public ForeignExchangeDriver()
    {
        file_path = "foreign.judge.in.txt";
    }
    
    /**
     * 
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException{
        ForeignExchangeDriver myHM = new ForeignExchangeDriver();
        
        myHM.readFile();
    }
    
    public void readFile() throws IOException
    {
        FileReader filereader = new FileReader(file_path);
        BufferedReader buff = new BufferedReader(filereader);
        String line = "";
        String data = "";
        
        while(!((line = buff.readLine()).equals("0")))
        {
            //System.out.println(line);
            hm = new HashMap<>();
            for(int i=0;i<Integer.parseInt(line);i++)
            {
                data = buff.readLine();
                String[] fields = data.split(" ");
                int a = Integer.parseInt(fields[0]);
                int b = Integer.parseInt(fields[1]);
                fillHashMap(a,b);
            }
            //printMap();
            if(isValidExchange())
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
    
    public void fillHashMap(int a, int b)
    {
        if(!hm.containsKey(a))//check if key is contained
            hm.put(a, new ArrayList<ForeignExchange>());
        
        if(isFEContained(a,b))
            hm.get(a).get(hm.get(a).indexOf(new ForeignExchange(b,0))).count++;
        else
            hm.get(a).add(new ForeignExchange(b,1));
        
        if(isMatch(a,b))//if there is a FE object match
        {
            if(hm.get(b).get(hm.get(b).indexOf(new ForeignExchange(a,0))).count != 0)//don't change if count is 0
            {
                hm.get(b).get(hm.get(b).indexOf(new ForeignExchange(a,0))).count--;
                hm.get(a).get(hm.get(a).indexOf(new ForeignExchange(b,0))).count--;
            }
        }
    }
    public boolean isFEContained(int a, int b)
    {
        return(hm.get(a).indexOf(new ForeignExchange(b,0)) != -1);//true if contains this FE object at key 'a' already
    }
    
    public boolean isMatch(int a, int b)
    {
        return (hm.containsKey(b) && hm.get(b).contains(new ForeignExchange(a,0)));
    }
    
    public boolean isValidExchange()
    {
        for(Integer key : hm.keySet())
        {
            for(int i=0;i<hm.get(key).size();i++)
            {
                if(hm.get(key).get(i).count!=0)
                    return false;
            }
        }
        return true;
    }
    
    public void printMap()
    {
        System.out.println(hm.entrySet());
    }
}
