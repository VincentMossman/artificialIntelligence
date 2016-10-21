package presentation;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

import component.TwiddleBoard;

/** 
 * The TwiddleSoverApp class uses a *modified* Breadth First Search to find 
 *  an optimal solution to any given Twiddle Board.
 *  
 * @author Vincent T. Mossman
 * @version September 28, 2015 (v2.1)
 */
public class TwiddleSolverApp 
{
	
    public static void main(String[] args)
    {
    	Scanner in = new Scanner(System.in);
    	String startBoard;
    	
    	while (true)
    	{
    		System.out.print("Enter the board as a single string of numbers 1-9: ");
    		startBoard = in.nextLine();
    		
    		if (startBoard == "")
    			break;
    	
    		System.out.println(bfs(startBoard) + "\n");
    	}
    	
    	in.close();
    }
    
    public static String bfs(String startBoard)
    {
    	int boardsConsidered = 0;
    	TwiddleBoard current = new TwiddleBoard(startBoard);
    	LinkedList<TwiddleBoard> theFringe = new LinkedList<TwiddleBoard>();
    	Hashtable<String, Boolean> expandedBoards = new Hashtable<String, Boolean>();
    	
    	theFringe.add(current);
    	
    	current = theFringe.remove();
    	
    	//MODIFIED BFS SEARCH
    	while (!current.winState())
    	{
    		//If we're here, 'current' must not be the goal board, so...
    		boardsConsidered++;
    		
    		//Expand child nodes
    		TwiddleBoard copyAC  = new TwiddleBoard(current);
    		TwiddleBoard copyACC = new TwiddleBoard(current);
    		TwiddleBoard copyBC  = new TwiddleBoard(current);
    		TwiddleBoard copyBCC = new TwiddleBoard(current);
    		TwiddleBoard copyCC  = new TwiddleBoard(current);
    		TwiddleBoard copyCCC = new TwiddleBoard(current);
    		TwiddleBoard copyDC  = new TwiddleBoard(current);
    		TwiddleBoard copyDCC = new TwiddleBoard(current);
    		
    		//Make child nodes useful
    		copyAC.AClockwise();
    		copyACC.ACounterClockwise();
    		copyBC.BClockwise();
    		copyBCC.BCounterClockwise();
    		copyCC.CClockwise();
    		copyCCC.CCounterClockwise();
    		copyDC.DClockwise();
    		copyDCC.DCounterClockwise();
    		
    		//Let's try to speed things up by skipping ahead a bit...
    		if (copyAC.winState())
    		{
    			current = copyAC;
    			break;
    		} else if (copyACC.winState())
    		{
    			current = copyACC;
    			break;
    		} else if (copyBC.winState())
    		{
    			current = copyBC;
    			break;
    		} else if (copyBCC.winState())
    		{
    			current = copyBCC;
    			break;
    		} else if (copyCC.winState())
    		{
    			current = copyCC;
    			break;
    		} else if (copyCCC.winState())
    		{
    			current = copyCCC;
    			break;
    		} else if (copyDC.winState())
    		{
    			current = copyDC;
    			break;
    		} else if (copyDCC.winState())
    		{
    			current = copyDCC;
    			break;
    		}
    		
    		//Now, let's only add unexplored nodes to the fringe...
    		if (!expandedBoards.containsKey(copyAC.getcurrentBoard()))
    			theFringe.add(copyAC);
    		if (!expandedBoards.containsKey(copyACC.getcurrentBoard()))
    			theFringe.add(copyACC);
    		if (!expandedBoards.containsKey(copyBC.getcurrentBoard()))
    			theFringe.add(copyBC);
    		if (!expandedBoards.containsKey(copyBCC.getcurrentBoard()))
    			theFringe.add(copyBCC);
    		if (!expandedBoards.containsKey(copyCC.getcurrentBoard()))
    			theFringe.add(copyCC);
    		if (!expandedBoards.containsKey(copyCCC.getcurrentBoard()))
    			theFringe.add(copyCCC);
    		if (!expandedBoards.containsKey(copyDC.getcurrentBoard()))
    			theFringe.add(copyDC);
    		if (!expandedBoards.containsKey(copyDCC.getcurrentBoard()))
    			theFringe.add(copyDCC);
    		
    		//Then, let's keep track of visited nodes...
    		expandedBoards.put(copyAC.getcurrentBoard(), true);
    		expandedBoards.put(copyACC.getcurrentBoard(), true);
    		expandedBoards.put(copyBC.getcurrentBoard(), true);
    		expandedBoards.put(copyBCC.getcurrentBoard(), true);
    		expandedBoards.put(copyCC.getcurrentBoard(), true);
    		expandedBoards.put(copyCCC.getcurrentBoard(), true);
    		expandedBoards.put(copyDC.getcurrentBoard(), true);
    		expandedBoards.put(copyDCC.getcurrentBoard(), true);
    		
    		//Get next node from the fringe ready
    		current = theFringe.remove();
    	}
    	
    	return "This board can be solved by doing\n" + current.path() + "\nConsidered a total of " + boardsConsidered + " boards" + "\nFringe still contains " + theFringe.size() + " boards";
    }
}
