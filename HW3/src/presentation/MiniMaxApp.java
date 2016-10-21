package presentation;

import java.util.ArrayList;
import java.util.Scanner;

//Author  Vincent T. Mossman
//Version 22 October 2015 (v1.0)
public class MiniMaxApp 
{
	public static void main(String[] args)
	{
    	@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
    	String startBoard;
    	
    	while (true)
    	{
    		System.out.print("What is the current state of the board? ");
    		startBoard = in.nextLine();
    	
    		ArrayList<String> possibleMoves = successor(startBoard);
    		int bestMove = 0;
    		
    		for (int i = 0; i < possibleMoves.size(); i++)
    		{
    			if (MiniMax(possibleMoves.get(i), 0, 0, true) > MiniMax(possibleMoves.get(bestMove), 0, 0, true))
    			{
    				bestMove = i;
    			}
    		}
    		
    		System.out.println("You should put pieces in positions " + whatsDifferent(startBoard, possibleMoves.get(bestMove)));
    		if (MiniMax(possibleMoves.get(bestMove), 0, 0, true) > 0)
    		{
    			System.out.println("And you will eventually win.\n");
    		}
    		else
    		{
    			System.out.println("But you should give up, cause you're gonna lose anyway.\n");
    		}
    	}
	}
	//-----
	public static String whatsDifferent(String board1, String board2)
	{
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < board1.length(); i++)
		{
			if (board1.charAt(i) != board2.charAt(i))
			{
				if (temp.length() > 0)
				{
					temp.append(" and ");
				}
				temp.append(i + 1);
			}
		}
		return temp.toString();
	}
	//-----
	public static boolean winState(String board)
	{
		return board.contains("*********");
	}
	public static int eval(int depth, boolean maxTurn)
	{
		int temp = 10 - depth;
		if (!maxTurn)
		{
			temp *= -1;
		}
		return temp;
	}
	public static int MiniMax(String board, int e, int depth, boolean maxTurn)
	{
		if (winState(board))
		{
			return eval(depth, maxTurn);
		}
		String s1 = successor(board).get(e);
		if (winState(s1))
		{
			return eval(depth + 1, !maxTurn);
		}
		if (maxTurn)
		{
			int highest = 0;
			for (int i = 0; i < successor(s1).size(); i++)
			{
				int temp = MiniMax(s1, i, depth + 1, !maxTurn);
				if (temp > highest)
				{
					highest = temp;
				}
			}
			return highest;
		}
		else
		{
			int lowest = 11;
			for (int i = 0; i < successor(s1).size(); i++)
			{
				int temp = MiniMax(s1, i, depth + 1, !maxTurn);
				if (temp < lowest)
				{
					lowest = temp;
				}
			}
			return lowest;
		}
	}
	//-----
	public static ArrayList<String> successor(String board)
	{
		ArrayList<String> nextBoards = new ArrayList<String>();
		//Three Piece Moves
		for (int i = 0; i < 6; i++)
		{
			if (i < 3)
			{
				if (board.charAt(i) != '*' &&
					board.charAt(i + 3) != '*' &&
					board.charAt(i + 6) != '*')
				{
					StringBuilder temp = new StringBuilder(board);
					temp.setCharAt(i, '*');
					temp.setCharAt(i + 3, '*');
					temp.setCharAt(i + 6, '*');
					nextBoards.add(temp.toString());
				}
			}
			else
			{
				int[] sideways = {0,3,6};
				if (board.charAt(sideways[i - 3]) != '*' &&
					board.charAt(sideways[i - 3] + 1) != '*' &&
					board.charAt(sideways[i - 3] + 2) != '*')
				{
					StringBuilder temp = new StringBuilder(board);
					temp.setCharAt(sideways[i - 3], '*');
					temp.setCharAt(sideways[i - 3] + 1, '*');
					temp.setCharAt(sideways[i - 3] + 2, '*');
					nextBoards.add(temp.toString());
				}
			}
		}
		//Two Piece Moves
		for (int i = 0; i < 18; i++)
		{
			if (i < 6)
			{
				if (board.charAt(i) != '*' && board.charAt(i + 3) != '*')
				{
					StringBuilder temp = new StringBuilder(board);
					temp.setCharAt(i, '*');
					temp.setCharAt(i + 3, '*');
					nextBoards.add(temp.toString());
				}
			}
			else if (i < 12)
			{
				int[] sideways = {0,1,3,4,6,7};
				if (board.charAt(sideways[i - 6]) != '*' && 
					board.charAt(sideways[i - 6] + 1) != '*')
				{
					StringBuilder temp = new StringBuilder(board);
					temp.setCharAt(sideways[i - 6], '*');
					temp.setCharAt(sideways[i - 6] + 1, '*');
					nextBoards.add(temp.toString());
				}
			}
			else if (i < 15)
			{
				int[] sideways = {0,3,6};
				if (board.charAt(sideways[i - 12]) != '*' &&
					board.charAt(sideways[i - 12] + 2) != '*')
				{
					StringBuilder temp = new StringBuilder(board);
					temp.setCharAt(sideways[i - 12], '*');
					temp.setCharAt(sideways[i - 12] + 2, '*');
					nextBoards.add(temp.toString());
				}
			}
			else
			{
				if (board.charAt(i - 15) != '*' &&
					board.charAt(i - 9) != '*')
				{
					StringBuilder temp = new StringBuilder(board);
					temp.setCharAt(i - 15, '*');
					temp.setCharAt(i - 9, '*');
					nextBoards.add(temp.toString());
				}
			}
		}
		//One Piece Moves
		for (int i = 0; i < 9; i++)
		{
			if (board.charAt(i) != '*')
			{
				StringBuilder temp = new StringBuilder(board);
				temp.setCharAt(i, '*');
				nextBoards.add(temp.toString());
			}
		}
		return nextBoards;
	}	
}
