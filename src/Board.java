import java.util.*;
public class Board 
{
	int[][] game;
	
	//constructor
	public Board ()
	{
		game=new int[7] [7];
		int counter=1;
		for (int i=6; i>-1; i--)
		{
			for (int j=0; j<7; j++)
			{
				game [i][j]=counter;
				counter++;
			}
		}
	}
	
	/*
	 * Places the player's pieces and removes the tiles already removed
	 * @param posCross represents the tile number of the posCross piece 
	 * @param posX represents the tile number of the posX piece 
	 * @param posTiles is an ArrayList storing the tile numbers of the tiles that have already been removed
	 */
	public void placePieces (int posCross, int posX, ArrayList<Integer> posTiles)
	{
		//find row and column of posX (one player's piece)
		int row=6-posX/7;
		int col=posX%7-1;
		if (posX%7==0) //exception : the position of the piece is divisible by 7
		{
			col=6;
			row++;
		}
		game [row][col]=100; //instead of tile number, the tile now has 100 to represent this piece
		
		//find row and column of posCross (the other player's piece)
		row=6-posCross/7;
		col=posCross%7-1;
		if (posCross%7==0)//exception : the position of the piece is divisible by 7
		{
			col=6;
			row++;
		}
		game [row][col]=200; //instead of tile number, the tile now has 200 to represent this piece
		
		//removes the tiles that the user has entered as already removed
		for (int i =0; i<posTiles.size(); i++)
		{
			row=6-posTiles.get(i).intValue()/7;
			col=posTiles.get(i).intValue()%7-1;
			if (posTiles.get(i).intValue()%7==0) //exception : the position of the piece is divisible by 7
			{
				col=6;
				row++;
			}
			game [row][col]=0; //instead of the tile number, the title now has 0 to represent it being removed
		}
	}
	
	/*
	 * prints the tile values (either the number of the tile, 0, 100, or 200) 
	 * each row has it's own line
	 */
	public void print ()
	{
		for (int i=0; i<7; i++)
		{
			for (int j=0; j<7; j++)
			{
				System.out.print (game[i][j]+ " ");
			}
			System.out.println ();
		}
	}
	
	/*
	 * Finds all of the possible paths that the posX piece can make
	 * @param posX represents the tile number of the posX piece 
	 * @return all of the possible paths; each path is an ArrayList of Integers, with each Integer equal to a tile number
	 */
	private ArrayList<ArrayList<Integer>> findPaths (int posX)
	{
		ArrayList<ArrayList<Integer>> paths=new ArrayList<ArrayList<Integer>> ();
		
		//find row and column for posX
		int row=6-posX/7;
		int col=posX%7-1;
		if (posX%7==0) //exception : the position of the piece is divisible by 7
		{
			col=6;
			row++;
		}
		
		paths.add(diagonalUpperLeft (row, col));
		paths.add(verticalUp (row, col));
		paths.add(diagonalUpperRight (row, col));
		paths.add(horiztonalRight (row, col));
		paths.add(diagonalLowerRight (row, col));
		paths.add(verticalDown (row, col));
		paths.add(diagonalLowerLeft (row, col));
		paths.add(horizontalRight (row, col));
		//System.out.println (paths);
		for (int x=0; x<paths.size(); x++)
		{
			ArrayList<Integer> l=paths.get(x);
			ArrayList<Integer> z=new ArrayList<Integer> ();
			if (l.contains (new Integer (0))) //this path crosses a removed tile
			{
				//the path is only valid up to the removed tile
				z=new ArrayList<Integer>(l.subList(0,l.indexOf(new Integer (0)))); //the valid path
				paths.add(z);
				//can't delete l without interfering with the iteration through paths because x = paths.size()
				l.clear(); //instead of deleting clears this path so that it's size = 0
			}
			if (l.contains (new Integer (100))) //this path crosses the position of posX
			{
				z=new ArrayList<Integer>(l.subList(0,l.indexOf(new Integer (100))));
				paths.add(z);
				l.clear();
			}
			if (l.contains (new Integer (200))) //this path crosses the position of posCross
			{
				z=new ArrayList<Integer>(l.subList(0,l.indexOf(new Integer (200))));
				paths.add(z);
				l.clear();
			}
		}
		return paths;
	}
	
	/*
	 * calculates the path from posX in the diagonal direction (up and to the left)
	 * @param row is the row of posX
	 * @param col is the column of posX
	 * @return an ArrayList of Integers that represent the tiles in the path
	 */
	private ArrayList<Integer> diagonalUpperLeft (int row, int col)
	{
		ArrayList<Integer> path=new ArrayList<Integer>();
		row--;	//to move up
		col--;	//to move left
		while (row<7 && col<7 && row>-1 && col>-1) //still on the board
		{
			path.add(game [row][col]);
			row--;
			col--;
		}
		return path;
	}
	
	/*
	 * calculates the path from posX in the vertical direction (up)
	 * @param row is the row of posX
	 * @param col is the column of posX
	 * @return an ArrayList of Integers that represent the tiles in the path
	 */
	private ArrayList<Integer> verticalUp (int row, int col)
	{
		ArrayList<Integer> path=new ArrayList<Integer>();
		row--; //to move up
		while (row<7 && col<7 && row>-1 && col>-1) //still on the board
		{
			path.add(game [row][col]);
			row--;
		}
		return path;
	}
	
	/*
	 * calculates the path from posX in the diagonal direction (up and to the right)
	 * @param row is the row of posX
	 * @param col is the column of posX
	 * @return an ArrayList of Integers that represent the tiles in the path
	 */
	private ArrayList<Integer> diagonalUpperRight (int row, int col)
	{
		ArrayList<Integer> path=new ArrayList<Integer>();
		row--; //to move up
		col++; //to move to the right
		while (row<7 && col<7 && row>-1 && col>-1) //still on the board
		{
			path.add(game [row][col]);
			row--;
			col++;
		}
		return path;
	}
	
	/*
	 * calculates the path from posX in the horizontal direction (up)
	 * @param row is the row of posX
	 * @param col is the column of posX
	 * @return an ArrayList of Integers that represent the tiles in the path
	 */
	private ArrayList<Integer> horiztonalRight (int row, int col)
	{
		ArrayList<Integer> path=new ArrayList<Integer>();
		col++; //to move up
		while (row<7 && col<7 && row>-1 && col>-1) //still on the board
		{
			path.add(game [row][col]);
			col++;
		}
		return path;
	}
	
	/*
	 * calculates the path from posX in the diagonal direction (down and to the right)
	 * @param row is the row of posX
	 * @param col is the column of posX
	 * @return an ArrayList of Integers that represent the tiles in the path
	 */
	private ArrayList<Integer> diagonalLowerRight (int row, int col)
	{
		ArrayList<Integer> path=new ArrayList<Integer>();
		row++; //to move down
		col++; //to move right
		while (row<7 && col<7 && row>-1 && col>-1) //still on the board
		{
			path.add(game [row][col]);
			row++;
			col++;
		}
		return path;
	}
	
	/*
	 * calculates the path from posX in the vertical direction (down)
	 * @param row is the row of posX
	 * @param col is the column of posX
	 * @return an ArrayList of Integers that represent the tiles in the path
	 */
	private ArrayList<Integer> verticalDown (int row, int col)
	{
		ArrayList<Integer> path=new ArrayList<Integer>();
		row++; //to move down
		while (row<7 && col<7 && row>-1 && col>-1) //still on the board
		{
			path.add(game [row][col]);
			row++;
		}
		return path;
	}
	
	/*
	 * calculates the path from posX in the diagonal direction (down and to the left)
	 * @param row is the row of posX
	 * @param col is the column of posX
	 * @return an ArrayList of Integers that represent the tiles in the path
	 */
	private ArrayList<Integer> diagonalLowerLeft (int row, int col)
	{
		ArrayList<Integer> path=new ArrayList<Integer>();
		row++; //to move down
		col--; //to move left
		while (row<7 && col<7 && row>-1 && col>-1) //still on the board
		{
			path.add(game [row][col]);
			row++;
			col--;
		}
		return path;
	}
	
	/*
	 * calculates the path from posX in the horizontal direction (right)
	 * @param row is the row of posX
	 * @param col is the column of posX
	 * @return an ArrayList of Integers that represent the tiles in the path
	 */
	private ArrayList<Integer> horizontalRight (int row, int col)
	{
		ArrayList<Integer> path=new ArrayList<Integer>();
		col--; //to move right
		while (row<7 && col<7 && row>-1 && col>-1) //still on the board
		{
			path.add(game [row][col]);
			col--;
		}
		return path;
	}
	
	/*
	 * finds the tiles next to posCross
	 * @param posCross is the tile number of the posCross piece
	 * @return an ArrayList of Integers that represent the tiles next to posCross
	 */
	private ArrayList<Integer> findAdjacent (int posCross)
	{
		//find position of posCross
		int row=6-posCross/7;
		int col=posCross%7-1;
		if (posCross%7==0) //divisible by 7 exception
		{
			col=6;
			row++;
		}
		
		ArrayList<Integer> tiles=new ArrayList<Integer> ();
		if ((row-1)>0 && (row-1)<7 && (col-1)>0 && (col-1)<7) //check to make sure valid position 
		{
			tiles.add (game [row-1][col-1]); //upper left
		}
		if ((row-1)>0 && (row-1)<7 && (col)>0 && (col)<7)
		{
			tiles.add (game [row-1][col]); //up
		}
		if ((row-1)>0 && (row-1)<7 && (col+1)>0 && (col+1)<7)
		{
			tiles.add (game[row-1][col+1]); //upper right
		}
		if ((row)>0 && (row)<7 && (col+1)>0 && (col+1)<7)
		{
			tiles.add (game[row][col+1]); //right
		}
		if ((row+1)>0 && (row+1)<7 && (col+1)>0 && (col+1)<7)
		{
			tiles.add (game[row+1][col+1]); //lower right
		}
		if ((row+1)>0 && (row+1)<7 && (col)>0 && (col)<7)
		{
			tiles.add (game[row+1][col]); //lower
		}
		if ((row+1)>0 && (row+1)<7 && (col-1)>0 && (col-1)<7)
		{
			tiles.add (game[row+1][col-1]); //lower left 
		}
		if ((row)>0 && (row)<7 && (col-1)>0 && (col-1)<7)
		{
			tiles.add (game[row][col-1]); //left
		}
		for (int x=0; x<tiles.size(); x++)
		{
			if (tiles.get (x).intValue()==0)
			{
				tiles.remove (x); //this tile is deleted and unvalid
			}
		}
		return tiles;
	}
	
	/*
	 * calculates the path that posX should take
	 * @param posCross represents the tile number of the posCross piece 
	 * @param posX represents the tile number of the posX piece 
	 * @return an ArrayList of Integers that represent the tiles in the path
	 */
	public ArrayList<Integer> finalPath (int posX, int posCross)
	{
		ArrayList<Integer> Answer;
		ArrayList<ArrayList<Integer>> paths=this.findPaths (posX);
		ArrayList<Integer> adjacent=this.findAdjacent(posCross);
		for (ArrayList<Integer> list:paths)
		{
			if (list.size()!=0) //path isn't empty
			{
				for (int x=0; x<adjacent.size(); x++)
				{
					if (list.contains(adjacent.get(x))) 
					{
						return list;
					}
				}
			}
		}
		return null;
	}
}
