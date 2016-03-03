package foreignexchangedriver;

public class ForeignExchange {
    public int dest;
    public int count;
    
    public ForeignExchange(int dest, int count)
    {
        this.dest = dest;
        this.count = count;
    }
    
    public int getCount()
    {
        return count;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        ForeignExchange f = (ForeignExchange)obj;
        return(this.dest == f.dest);
    }
    
    
    @Override
    public String toString()
    {
        return dest + " " +count;
    }
}
