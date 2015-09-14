import java.util.*;
public class IsolaRunner 
{
	public static void main (String[] args)
	{
		//read in input
		Scanner input=new Scanner (System.in);
		int posCross=input.nextInt(); 
		input.nextLine();
		int posX=input.nextInt();
		input.nextLine();
		ArrayList<Integer> tiles=new ArrayList<Integer> ();
		int trial=input.nextInt();
		while (trial!=0)//0 signifies end of input
		{
			tiles.add(new Integer (trial));
			trial=input.nextInt();
		}

		
		Board isola=new Board (); //creates new board
		isola.placePieces(posCross, posX, tiles); //places the player's pieces and removes the tiles already removed
		ArrayList<Integer> answer=isola.finalPath(posX, posCross); //finds the correct path
		
		if (answer==null) //there is no valid path
		{
			System.out.println ("NONE");
		}
		else
		{
			if (answer.contains(new Integer (100)))
			{
				answer.remove(new Integer (100));
			}
			if (answer.contains(new Integer (200)))
			{
				answer.remove(new Integer (200));
			}
			//System.out.println (Answer);
			for (int c=0; c<answer.size(); c++)
			{
				System.out.print (answer.get (c)+" ");
			}
			System.out.println ();
		}
	
	}
}
