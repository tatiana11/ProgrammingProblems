
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author Tati
 * @date 02/29/2016
 * 
 * A Dijkstra problem where Bill and Ted have to find the shortest time travel
 * to a famous person.
 */

class Vertex implements Comparable<Vertex>{
    int year,pos,time, n;
    boolean visited;
    int[] weights;
    
    Vertex(int y, int i, int n){
       time=0;
       year=y;
       pos=i;
       visited=false;
       weights=new int[n];
    }

    @Override
    public int compareTo(Vertex t){
        return time-t.time;
    }
}

public class BillAndTed{
    //HashMap of int, Vertex, where Vertexes have adjList of int[] weights
    //Key values are positions 0-(N-1) because all weights index to these keys
    //in corresponding order.
    Map<Integer,Vertex> map;
    int N;
    
    public static void main(String[] args){
        BillAndTed bt = new BillAndTed();
        bt.run();
    }
    
    void run(){
        Scanner in = new Scanner(System.in);
        int F = in.nextInt();
        
        while(F--!=0){
            N = in.nextInt();
            map = new HashMap<>();
            int startPos = 0;
            
            for(int i=0;i<N;i++){
                int y=in.nextInt();
                if(y==1989)
                    startPos=i;
                Vertex v = new Vertex(y,i,N);
                
                for(int j=0;j<N;j++){
                    v.weights[j]=in.nextInt();
                }
                map.put(i,v);
            }
            int target = in.nextInt();
            int time = dijkstra(map.get(startPos),target);
            System.out.println(time);
        }
    }
    
    int dijkstra(Vertex source, int target){
        
        PriorityQueue<Vertex> pq= new PriorityQueue<>();
        pq.add(source);
        
        while(!pq.isEmpty()){
            Vertex v = pq.poll();
            if(map.get(v.pos).year==target){
                return v.time;
            }
            else if(!map.get(v.pos).visited){
                //Mark the Vertex in map as visited
                map.get(v.pos).visited=true;
                for(int i=0;i<N;i++){
                    //Adding brand new Vertex with empty adjList to pq if has not
                    //been visited. The corresponding key positions of currently 
                    //popped Vertex weights is added to current Vertex time if not
                    //visited
                    if(!map.get(i).visited){
                        Vertex u = new Vertex(map.get(i).year,i,0);
                        u.time = map.get(v.pos).weights[i]+v.time;
                        pq.add(u);
                    }
                }
            }
        }
        return -1;
    }
}
