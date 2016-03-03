  /*
 * A program that reads in a file of call forwarding systems

   An ArrayList of vertices is used, with each vertex containing a list of edges
   in order to represent a directed graph

   Each edge contains a weight representing a range of times in which a number
   will be forwarded to a certain extension
 */

package callforwarding;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Tati
 * 01/24/2016
 */

public class CallForwarding
{
    ArrayList<Vertex> vertices;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    {
        CallForwarding cf = new CallForwarding();
        cf.run();
    }
    
    void run() throws IOException
    {
        Scanner in = new Scanner(new FileReader(new File("callForwarding.in.txt")));
        
        int c = 0;
        int n = in.nextInt();
        in.nextLine();
        
        System.out.println("CALL FORWARDING OUTPUT");
        while(c++!=n)
        {
            System.out.println("System " + c);
            
            vertices = new ArrayList<>();
            String data = in.nextLine();
            
            while(Integer.parseInt(data.substring(0, 4))!=0000)
            {
                String fields[]=data.split(" ");
                
                int number = Integer.parseInt(fields[0]);
                int sTime = Integer.parseInt(fields[1]);
                int dur = Integer.parseInt(fields[2]);
                int extension = Integer.parseInt(fields[3]);
                
                Vertex v = new Vertex(number);
                if(!vertices.contains(v))
                {
                    v.edges.add(new Edge(extension,sTime,sTime+dur));
                    vertices.add(v);
                }
                else
                {
                    for(int i=0; i<vertices.size(); i++)
                    {
                        if(vertices.get(i).num == number)
                        {
                            vertices.get(i).edges.add(new Edge(extension,sTime,sTime+dur));
                            break;
                        }
                    }
                }
                        
                data=in.nextLine();
            }
            
            data=in.nextLine();
            
            while(Integer.parseInt(data.substring(0, 4))!=9000)
            {
                String fields[]=data.split(" ");
                
                int time = Integer.parseInt(fields[0]);
                int callTo = Integer.parseInt(fields[1]);
                
                int rings = rings(time,callTo);
                
                System.out.println("AT "+ String.format("%04d", time) + " CALL TO " + callTo + " RINGS " + rings);
                
                data=in.nextLine();
            }
            
        }
    }
    
    //Recursive method used to check if the calling number is logged in the system
    int rings(int time, int callTo)
    {
        int r=0;
        for(int i=0; i<vertices.size(); i++)//iterate through the numbers in the forwarding system
        {
            //if the calling number was found in the system, 
            //check if it has already been visited or call rings2 method
            if(vertices.get(i).num == callTo) 
            {
                if(vertices.get(i).visited)
                {
                    r= 9999;
                    break;
                } 
                else
                {
                    r= rings2(i,time,callTo);
                    break;
                }
            }
        }
        
        //if r is 0, then the calling number is not logged into the 
        //forwarding system, return the calling number
        if(r==0) 
            r=callTo;
        
        return r;
    }
    
    //Recursive helper method to check the List of logged extensions at specific times
    int rings2(int i, int time, int callTo)
    {
        int r=0;
        
        //iterate through the list of edges corresponding to the specified vertex
        for(int j=0; j<vertices.get(i).edges.size();j++) 
        {
            //if the time is between the start and end times of the edge, 
            //then the rings method is called with a new callTo value
            if(time>=vertices.get(i).edges.get(j).startTime && time<=vertices.get(i).edges.get(j).endTime) 
            {
                vertices.get(i).visited = true; 
                r= rings(time,vertices.get(i).edges.get(j).name);
                //after recursive method rings returns, the visited
                //value is set back to false for the next call in line;
                vertices.get(i).visited = false; 
                break;   
            }
        }
        //if time did not fall into any of the edges in the list, return the vertex number
        if(r==0)
            r= vertices.get(i).num;
        
        return r;
    }
    
}
