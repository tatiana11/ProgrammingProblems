import java.util.Scanner;

/**
 * @author Tati
 */

public class EmasSupercomputer
{
    char map[][];
    int N, M;
    int[] _r = {1,-1,0,0};
    int[] _c = {0,0,1,-1};
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        EmasSupercomputer ema = new EmasSupercomputer();
        ema.run();
    }
    
    void run()
    {
        Scanner in = new Scanner(System.in);
        
        String line;
        N=in.nextInt();
        M=in.nextInt();
        
        map = new char[N][M];
        
        in.nextLine();
        
        for(int i=0;i<N;i++)
        {
            line=in.nextLine();
            for(int j=0;j<M;j++)
            {
                map[i][j]= line.charAt(j);
            }
        }
        
        int l1, l2;
        int area=0;
        
        for(int r1=0;r1<N;r1++)
        for(int c1=0;c1<M;c1++){
            if(map[r1][c1] =='G')
            {
                boolean done1 = false;
                map[r1][c1]='X';
                l1=1;
                
                for(int r2=0;r2<N;r2++)
                for(int c2=0;c2<M;c2++){
                    
                    if(map[r2][c2] =='G')
                    {
                        map[r2][c2]='X';
                        l2=1;
                        boolean done = false;
                        boolean done2 = false;
                        
                         while(!done){
                            if(!done1)
                            {
                                for(int k = 0 ; k < 4 ; k++){
                                    int r3 = r1 + _r[k]*l1;
                                    int c3 = c1 + _c[k]*l1;

                                    done1 = ifOutboundsandBad(r3,c3);
                                    
                                    if(done1)
                                        break;
                                }                                
                            }
                            
                            if(!done1)
                            {
                                markCells(r1, c1, l1);
                                l1++;
                            }
                                
                            if(!done2)
                            {
                                for(int k = 0 ; k < 4 ; k++){
                                    int r4 = r2 + _r[k]*l2;
                                    int c4 = c2 + _c[k]*l2;

                                    done2 = ifOutboundsandBad(r4,c4);
                                    
                                    if(done2)
                                        break;
                                }
                            }
                            
                            if(!done2)
                            {
                                markCells(r2, c2, l2);
                                l2++;
                            }
                            
                            
                            if(done1&&done2)
                            {
                                int tempArea=((l1-1)*4+1)*((l2-1)*4+1);
                                if(tempArea > area)
                                    area=tempArea;
                                
                                //printMap();
                                
                                if(r2==(N-1))
                                    unmarkCells(r1, c1, l1);
                                unmarkCells(r2, c2, l2);
                                
                                done=true;
                            }
                            
                         }
                    }  
                }
            }
        }
        
    System.out.println(area);
            
    }
    
    boolean ifOutboundsandBad(int r, int c)
    {
        return (r < 0 || r >= N || c < 0 || c >= M || map[r][c] == 'B' || map[r][c] == 'X');
    }
    
    void markCells(int r, int c, int l)
    {
        for(int k = 0 ; k < 4 ; k++){
            map[r + _r[k]*l][c + _c[k]*l] = 'X';
        }     
    }
    
    void unmarkCells(int r, int c, int l)
    {
        while(l--!=0)
        {
            for(int k = 0 ; k < 4 ; k++){
                map[r + _r[k]*l][c + _c[k]*l] = 'G';
            } 
        }
    }
    
    void printMap()
    {
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
            
        
    }
        
}

