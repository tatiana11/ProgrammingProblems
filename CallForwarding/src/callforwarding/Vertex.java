package callforwarding;

import java.util.ArrayList;

public class Vertex {
    int num;
    boolean visited;
    ArrayList<Edge> edges;
    
    public Vertex(int n)
    {
        num=n;
        visited = false;
        edges = new ArrayList<>();
    }
    
   
    @Override
    public boolean equals(Object o)
    {
        return num == ((Vertex) o).num;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 19 * hash + this.num;
        return hash;
    }
    
}
