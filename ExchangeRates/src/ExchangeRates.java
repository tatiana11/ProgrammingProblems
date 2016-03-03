
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Tati
 * @date 03/02/2016
 * 
 * Sample input:
 * ! 6 shirt = 15 sock
 * ! 47 underwear = 9 pant
 * ? sock = shirt
 * ? shirt = pant
 * ! 2 sock = 1 underwear
 * ? pant = shirt
 * ! 3 sweater = 4 sock
 * ? pant = sweater
 * .
 * 
 */
class Main{
    
    Map<String,Integer> lut;
    int[][] exchangeTable;
    int[] visited;
    int itemNum=0, A, B;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Main m = new Main();
        m.run();
    }
    
    void run(){
        lut = new HashMap<>();
        exchangeTable = new int[60][60];
        visited = new int[60];
        
        String[] fields;
        Scanner in = new Scanner(System.in);
        
        do{
            fields = in.nextLine().split(" ");
            
            if(fields[0].charAt(0)=='!'){
                int i1 = Integer.parseInt(fields[1]);
                int i2 = Integer.parseInt(fields[4]);
                int gcd = gcd(i1,i2);
                i1/=gcd;
                i2/=gcd;
                int i = getIndex(fields[2]);
                int j = getIndex(fields[5]);
                exchangeTable[i][j]=i1;
                exchangeTable[j][i]=i2;
                
            }else if(fields[0].charAt(0)=='?'){
                clearVisited();
                A=1;
                B=1;
                if(dfs(getIndex(fields[1]),getIndex(fields[1]),getIndex(fields[3])))
                    System.out.println(A+" "+fields[1]+" = "+B+" "+fields[3]);
                else
                    System.out.println("? "+fields[1]+" = ? "+fields[3]);
            }
            
        }while(fields[0].charAt(0)!='.');
        
        //printLUT();
        //printTable();
    }
    
    boolean dfs(int s, int i, int t){
        if(visited[i]==1){
            return false;
        }
        if(exchangeTable[i][t]!=0 && exchangeTable[t][i]!=0){
            A*=exchangeTable[i][t];
            B*=exchangeTable[t][i];
            int gcd = gcd(A,B);
            A/=gcd;
            B/=gcd;
            //Fill table with found exchange rates if empty
            if(exchangeTable[s][t]==0){
                exchangeTable[s][t]=A;
                exchangeTable[t][s]=B;
            }
            return true;
        }
        for(int j=0;j<itemNum;j++){//loop through row
            if(exchangeTable[i][j]!=0){//if found non empty slot
                int tempA = exchangeTable[i][j];
                int tempB = exchangeTable[j][i];
                A*=exchangeTable[i][j];
                B*=exchangeTable[j][i];
                int gcd = gcd(A,B);
                A/=gcd;
                B/=gcd;
                visited[i]=1;
                if(!dfs(s,j,t)){//backtrack
                    A*=gcd;
                    B*=gcd;
                    A/=tempA;
                    B/=tempB;
                    visited[i]=0;
                }
                else
                    return true;
            }
        }
        return false; //if gone through entire visited row(s)
    }
    
    void clearVisited(){
        for(int i=0;i<60;i++)
            visited[i]=0;
    }
    
    int gcd(int a, int b){
      if(a == 0 || b == 0) return a+b; 
      return gcd(b,a%b);
    }
    
    int getIndex(String s){
        if(!lut.containsKey(s))
            lut.put(s,itemNum++);
        
        return lut.get(s);
    }
    
    void printLUT(){
        System.out.println(lut.entrySet());
    }
    
    void printTable(){
        for(int i=0;i<60;i++){
            for(int j=0;j<60;j++){
                System.out.print(exchangeTable[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
}
