package component;

/** 
 * The Board class represents a Twiddle board that can be manipulated
 *  by rotating tiles around four vertices. This class also implements
 *  some basic 'node' functionality, such as:
 *  	- Each board knows its 'parent' board
 *  	- Each board can remember the transition made from its 'parent' board
 *  	- Each board can report the complete path taken through its ancestors
 * 
 * @author Vincent T. Mossman
 * @version September 27, 2015 (v2.0)
 */
public class TwiddleBoard 
{
	//Instance Variable
	protected String currentBoard;
	protected TwiddleBoard parentBoard;
	protected String transition;
	
	//Constructors
	public TwiddleBoard()
	{
		this.currentBoard = "123456789";
		this.parentBoard = null;
		this.transition = "";
	}
	
	public TwiddleBoard(String inString)
	{
		this.currentBoard = inString;
		this.parentBoard = null;
		this.transition = "";
	}
	
	public TwiddleBoard(String inString, TwiddleBoard inParentBoard, String inTransition)
	{
		this.currentBoard = inString;
		this.parentBoard = inParentBoard;
		this.transition = inTransition;
	}
	
	public TwiddleBoard(TwiddleBoard inTwiddleBoard)
	{
		this(inTwiddleBoard.currentBoard, inTwiddleBoard, inTwiddleBoard.transition);
	}
	
	//Methods
	public String getcurrentBoard()
	{
		return this.currentBoard;
	}
	
	public boolean winState()
	{
		return (this.currentBoard.contains("123456789"));
	}
	
	public String path()
	{
		String path = "";
		TwiddleBoard current = this;
		
		while (current != null)
		{
			path = current.transition + " " + path;
			current = current.parentBoard;
		}
		
		return path.trim();
	}

	//-------
	public void AClockwise()
	{
		this.currentBoard = AClockwise(this.currentBoard);
		this.transition = "aC";
	}
	
	public void ACounterClockwise()
	{
		this.currentBoard = ACounterClockwise(this.currentBoard);
		this.transition = "aCC";
	}
	
	public void BClockwise()
	{
		this.currentBoard = BClockwise(this.currentBoard);
		this.transition = "bC";
	}
	
	public void BCounterClockwise()
	{
		this.currentBoard = BCounterClockwise(this.currentBoard);
		this.transition = "bCC";
	}
	
	public void CClockwise()
	{
		this.currentBoard = CClockwise(this.currentBoard);
		this.transition = "cC";
	}
	
	public void CCounterClockwise()
	{
		this.currentBoard = CCounterClockwise(this.currentBoard);
		this.transition = "cCC";
	}
	
	public void DClockwise()
	{
		this.currentBoard = DClockwise(this.currentBoard);
		this.transition = "dC";
	}
	
	public void DCounterClockwise()
	{
		this.currentBoard = DCounterClockwise(this.currentBoard);
		this.transition = "dCC";
	}
	
	//Helper Functions
    private static String AClockwise(String inString)
    {
    	return "" + inString.charAt(3) + inString.charAt(0) + inString.charAt(2) + inString.charAt(4) + inString.charAt(1) + inString.charAt(5) + inString.charAt(6) + inString.charAt(7) + inString.charAt(8);
    }
    
    private static String ACounterClockwise (String inString)
    {
    	return "" + inString.charAt(1) + inString.charAt(4) + inString.charAt(2) + inString.charAt(0) + inString.charAt(3) + inString.charAt(5) + inString.charAt(6) + inString.charAt(7) + inString.charAt(8); 
    }
    
    private static String BClockwise (String inString)
    {
    	return "" + inString.charAt(0) + inString.charAt(4) + inString.charAt(1) + inString.charAt(3) + inString.charAt(5) + inString.charAt(2) + inString.charAt(6) + inString.charAt(7) + inString.charAt(8); 
    }
    
    private static String BCounterClockwise (String inString)
    {
    	return "" + inString.charAt(0) + inString.charAt(2) + inString.charAt(5) + inString.charAt(3) + inString.charAt(1) + inString.charAt(4) + inString.charAt(6) + inString.charAt(7) + inString.charAt(8); 
    }
    
    private static String CClockwise (String inString)
    {
    	return "" + inString.charAt(0) + inString.charAt(1) + inString.charAt(2) + inString.charAt(6) + inString.charAt(3) + inString.charAt(5) + inString.charAt(7) + inString.charAt(4) + inString.charAt(8); 
    }
    
    private static String CCounterClockwise (String inString)
    {
    	return "" + inString.charAt(0) + inString.charAt(1) + inString.charAt(2) + inString.charAt(4) + inString.charAt(7) + inString.charAt(5) + inString.charAt(3) + inString.charAt(6) + inString.charAt(8); 
    }
    
    private static String DClockwise (String inString)
    {
    	return "" + inString.charAt(0) + inString.charAt(1) + inString.charAt(2) + inString.charAt(3) + inString.charAt(7) + inString.charAt(4) + inString.charAt(6) + inString.charAt(8) + inString.charAt(5); 
    }
    
    private static String DCounterClockwise (String inString)
    {
    	return "" + inString.charAt(0) + inString.charAt(1) + inString.charAt(2) + inString.charAt(3) + inString.charAt(5) + inString.charAt(8) + inString.charAt(6) + inString.charAt(4) + inString.charAt(7); 
    }
}
