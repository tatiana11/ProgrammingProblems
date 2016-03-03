
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Tati
 * @date 02/16/2016
 * 
 * This program uses BFS to find the shortest amount of knight moves on a 
 * chessboard necessary to get from a source Cell to a target Cell
 */

class Cell{
    int row;
    int col;
    int dist=0;
    char value=KnightMoves.EMPTYCELL;
    
    Cell(int r, int c){
        row=r;
        col=c;
    }
    
    @Override
    public String toString(){
        return ""+value;
    }
}
public class KnightMoves
{
    public static final int ROWS=8;
    public static final int COLS=8;
    public static final char EMPTYCELL='-';
    Cell chessBoard[][];

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    {
        KnightMoves knight = new KnightMoves();
        knight.run();
    }
    
    void run() throws IOException {
        Scanner in = new Scanner(new FileReader(new File("knights.in.txt")));
        while(in.hasNextLine()){
            
            chessBoard = new Cell[ROWS][COLS];
            
            for(int i=0; i<ROWS;i++){
                for(int j=0; j<COLS; j++){
                    chessBoard[i][j] = new Cell(i,j);
                }
            }
            
            String cells[] = in.nextLine().split(" ");
            int r1 = (int)cells[0].charAt(1) - 49;
            int c1 = (int)cells[0].charAt(0) - 97;
            int r2 = (int)cells[1].charAt(1) - 49;
            int c2 = (int)cells[1].charAt(0) - 97;
            
            chessBoard[r1][c1].value='S';
            chessBoard[r2][c2].value='T';
            
            int moves = bfs(chessBoard[r1][c1]);
            
            printBoard();
            System.out.println("To get from "+cells[0]+" to "+cells[1]+" takes "
                                + moves + " knights moves.");
            
        }
    }
    
    int bfs(Cell source){
        int pos[]={-1,2,1,2,-1,-2,1,-2,2,1,2,-1,-2,1,-2,-1};
        
        Queue<Cell> q = new LinkedList<>();
        q.add(source);
        
        while(!q.isEmpty()){
            Cell c = q.poll();
            if(c.value!='T'){
                for(int i=0;i<pos.length-1;i+=2){
                    int _r = c.row+pos[i];
                    int _c = c.col+pos[i+1];


                    if(!outofbounds(_r,_c)){
                        if(chessBoard[_r][_c].value=='T')
                            return c.dist+1;

                        if(chessBoard[_r][_c].value==EMPTYCELL){
                            int d = c.dist+1;
                            chessBoard[_r][_c].dist=d;
                            chessBoard[_r][_c].value=(char)(d+'0');
                            q.add(chessBoard[_r][_c]);
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    boolean outofbounds(int r, int c){
        return r<0||r>=ROWS||c<0||c>=COLS;
    }
    void printBoard(){
        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLS;j++){
                System.out.print(chessBoard[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
}
