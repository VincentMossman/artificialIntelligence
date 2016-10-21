package genetics;

import java.util.Random;

public class Chromosome 
{
	//Variables
	String r, g, b, bitString;
	String target;
	int minTicket, maxTicket;
	
	//Constructors
	public Chromosome()
	{
		Random rng = new Random();
		this.bitString = "";
		String sub;
		
		for (int j = 0; j < 3; j++)
		{
			sub = Integer.toBinaryString(rng.nextInt(255));
			for (int i = sub.length(); i < 8; i++)
			{
				sub = "0" + sub;
			}
			
			this.bitString = sub + this.bitString;
		}
		
        this.r = this.bitString.substring(0, 8);
        this.g = this.bitString.substring(8, 16);
        this.b = this.bitString.substring(16, 24);
	}
	
	public Chromosome(String inBitString)
	{
		this.bitString = inBitString;
        this.r = inBitString.substring(0, 8);
        this.g = inBitString.substring(8, 16);
        this.b = inBitString.substring(16, 24);
	}
	
	//Identity
	public String getBitString()
	{
		return this.bitString;
	}
	
    public String getR()
    {
        return this.r;
    }
    
    public String getG()
    {
        return this.g;
    }
    
    public String getB()
    {
        return this.b;
    }
    
    //-----
    public int getIntegerR()
    {
        return Integer.parseInt(this.r,2);
    }
    
    public int getIntegerG()
    {
        return Integer.parseInt(this.g,2);
    }
    
    public int getIntegerB()
    {
        return Integer.parseInt(this.b,2);
    }
    
    //-----
    public int getMinTicket()
    {
        return this.minTicket;
    }
    
    public int getMaxTicket()
    {
        return this.maxTicket;
    }
	
	//Methods
    public void changeBit(int index)
    {
        StringBuilder newString = new StringBuilder(this.bitString);
        
        if(newString.charAt(index) == '0')
        { 
        	newString.setCharAt(index, '1'); 
        }
        else
        { 
        	newString.setCharAt(index, '0');
        }
        
        this.bitString = newString.toString();
    }
    
    public double getFittness(Chromosome inTarget)
    {
        return Math.sqrt(Math.pow(this.getIntegerB()-inTarget.getIntegerB(), 2)
                +Math.pow(this.getIntegerG()-inTarget.getIntegerG(), 2)
                +Math.pow(this.getIntegerR()-inTarget.getIntegerR(), 2) );
    }
    
    public void setMinTicket(int inMin)
    {
        this.minTicket = inMin;
    }
    
    public void setMaxTicket(int inMax)
    {
        this.maxTicket = inMax;
    }
}
