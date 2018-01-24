/**
* This is the greedy algorithm for pentominoes for the knapsack problem
*
* @version 1
*/

import java.util.Arrays;

public class GreedyP
{

  	private static int[][][] truckspace = new int[10][10][10];
  	private static int totalValue = 0;
  	private static int amountOfP = 1000000;
  	private static int amountOfL = 0;
	private static int amountOfT = 0;
	private static int counterOfP = 0;
	private static int counterOfL = 0;
	private static int counterOfT = 0;

  	private static int[][][][] piece =
		{{{{1,1},{1,1},{1,0}}}, //P
		{{{1,0},{1,0},{1,1}}},
		{{{1,1},{1,1},{0,1}}},
		{{{0,1},{1,1},{1,1}}},

		{{{1,1,1},{1,1,0}}},
		{{{1,1,1},{0,1,1}}},
		{{{0,1,1},{1,1,1}}},
		{{{1,1,0},{1,1,1}}},

		{{{1},{1}},{{1},{1}},{{1},{0}}},
		{{{1},{1}},{{1},{1}},{{0},{1}}},
		{{{1},{0}},{{1},{1}},{{1},{1}}},
		{{{0},{1}},{{1},{1}},{{1},{1}}},

		{{{1},{1},{1}},{{0},{1},{1}}},
		{{{1},{1},{1}},{{1},{1},{0}}},
		{{{0},{1},{1}},{{1},{1},{1}}},
		{{{1},{1},{0}},{{1},{1},{1}}},

		{{{1,1,1}},{{1,1,0}}},
		{{{1,1,1}},{{0,1,1}}},
		{{{0,1,1}},{{1,1,1}}},
		{{{1,1,0}},{{1,1,1}}},

		{{{1,1}},{{1,1}},{{1,0}}},
		{{{1,1}},{{1,1}},{{0,1}}},
		{{{0,1}},{{1,1}},{{1,1}}},
		{{{1,0}},{{1,1}},{{1,1}}},

		{{{0,2},{0,2},{0,2},{2,2}}}, //L
		{{{2,0},{2,0},{2,0},{2,2}}},
		{{{2,2},{0,2},{0,2},{0,2}}},
		{{{2,2},{2,0},{2,0},{2,0}}},

		{{{2,2,2,2},{0,0,0,2}}},
		{{{2,2,2,2},{2,0,0,0}}},
		{{{0,0,0,2},{2,2,2,2}}},
		{{{2,0,0,0},{2,2,2,2}}},

		{{{2},{2}},{{2},{0}},{{2},{0}},{{2},{0}}},
		{{{2},{2}},{{0},{2}},{{0},{2}},{{0},{2}}},
		{{{2},{0}},{{2},{0}},{{2},{0}},{{2},{2}}},
		{{{0},{2}},{{0},{2}},{{0},{2}},{{2},{2}}},

		{{{2},{2},{2},{2}},{{2},{0},{0},{0}}},
		{{{2},{2},{2},{2}},{{0},{0},{0},{2}}},
		{{{0},{0},{0},{2}},{{2},{2},{2},{2}}},
		{{{2},{0},{0},{0}},{{2},{2},{2},{2}}},

		{{{2,2,2,2}},{{2,0,0,0}}},
		{{{2,2,2,2}},{{0,0,0,2}}},
		{{{2,0,0,0}},{{2,2,2,2}}},
		{{{0,0,0,2}},{{2,2,2,2}}},

		{{{2,2}},{{2,0}},{{2,0}},{{2,0}}},
		{{{2,2}},{{0,2}},{{0,2}},{{0,2}}},
		{{{2,0}},{{2,0}},{{2,0}},{{2,2}}},
		{{{0,2}},{{0,2}},{{0,2}},{{2,2}}},


		{{{3,3,3},{0,3,0},{0,3,0}}}, //T
		{{{0,3,0},{0,3,0},{3,3,3}}},
		{{{3,0,0},{3,3,3},{3,0,0}}},
		{{{0,0,3},{3,3,3},{0,0,3}}},

		{{{3},{3},{3}},{{0},{3},{0}},{{0},{3},{0}}},
		{{{0},{3},{0}},{{0},{3},{0}},{{3},{3},{3}}},
		{{{3},{0},{0}},{{3},{3},{3}},{{3},{0},{0}}},
		{{{0},{0},{3}},{{3},{3},{3}},{{0},{0},{3}}},

		{{{3,3,3}},{{0,3,0}},{{0,3,0}}},
		{{{0,3,0}},{{0,3,0}},{{3,3,3}}},
		{{{3,0,0}},{{3,3,3}},{{3,0,0}}},
		{{{0,0,3}},{{3,3,3}},{{0,0,3}}}
 	};

	/**
    *constructor of the class
    *
    *@param piece: 4D array with all the pentominoes in every orientation
    */
  	public GreedyP(int[][][][] piece)
  	{
    	this.piece = piece;
  	}

	/**
    * method that checks if there are still pentominoes of this type available and if yes increase the counter of the value
    *(because they will be placed) and decreases the amount of the pentomino.
    *
    *@param piece: 4D array with all the pentominoes in every orientation
    */
	public static boolean checkAmount(int[][][] piece)
 	{
   		if(piece[0][0][0]== 1 || piece[piece.length-1][piece[0].length-1][piece[0][0].length-1]==1)
   		{
     		if(counterOfP < amountOfP)
     		{
     			counterOfP++;
     			totalValue = totalValue + 3;
     			return true;
     		}
     		return false;
     	}
    	else if(piece[0][0][0]== 2 || piece[piece.length-1][piece[0].length-1][piece[0][0].length-1]==2)
    	{
     		if(counterOfL < amountOfL)
     		{
     			counterOfL++;
     			totalValue = totalValue + 1;
     			return true;
     		}
     		return false;
     	}
    	else //(piece[0][0][0]== 3 || piece[piece.length-1][piece[0].length-1][piece[0][0].length-1]==3)
    	{
     		if(counterOfT < amountOfT)
     		{
     			counterOfT++;
     			totalValue = totalValue + 1;
     			return true;
     		}
     		return false;
     	}
  	}

	/**
    *checks if the pentomino fits in the empty space
    *
    *@param type: type of pentomino used
    *@param d, h, w: dimensions of the pentomino
    */
  	public static boolean checkFitOfP(int type, int d, int h, int w)
	{
	    for (int i=0; i< piece[type].length; i++)
			for (int j=0; j< piece[type][0].length; j++)
				for (int k=0; k< piece[type][0][0].length; k++)
					if(piece[type][i][j][k]!=0)
					{
						if(i+d >= truckspace.length||j+h >= truckspace[0].length||k+w >= truckspace[0][0].length)
			        		return false;
						if(truckspace[i+d][j+h][k+w] !=0)
							return false;

					}
		return true;
	}
  
 	/**
    *places the pentomino in the empty space
    *
    *@param type: type of pentomino used
    *@param d, h, w: dimensions of the pentomino
    */
	public static void putP(int type, int d, int h, int w)
	{
		for (int i = 0; i < piece[type].length ; i ++)
			for (int j = 0; j < piece[type][0].length ; j ++)
				for (int k = 0; k < piece[type][0][0].length ; k ++)
					if(piece[type][i][j][k]!=0)
						truckspace[d+i][h+j][w+k] = piece[type][i][j][k];
	}

	/**
    *the method which decides if the pentomino in this orientation will be put into the truck
    *
    */
  	public static void solve()
  	{
  		for(int i=0; i<piece.length; i++)
	      	for(int x = 0; x < truckspace.length; x++)
	        	for(int y = 0; y < truckspace[0].length; y++)
	          		for(int z = 0; z < truckspace[0][0].length; z++)
	            			if(checkFitOfP(i, x, y, z))
	            				if(checkAmount(piece[i])== true) // checking if that pentomino is stil available
	            					putP(i, x, y, z);
	}

	public static void main(String[] args)
	{
	   	GreedyP algorithm = new GreedyP(piece);

	    algorithm.solve();

	/**
    * prints out the truck in 'slices' to visualize the cargo- space better
    *
    */
	    for(int x = 0; x < truckspace.length; x++)
	    {
	      	if(x % 2==0)
	      	{
	        	System.out.println((x/2) + " m");
	        	System.out.println();
	      	}
	      	for(int y = 0; y < truckspace[0].length; y++)
	      	{
	        	for(int z = 0; z < truckspace[0][0].length; z++)
	          		System.out.print(truckspace[x][y][z] + " ");
	        	System.out.println();
	      	}
	      	System.out.println();
	    }
	    //System.out.println(Arrays.deepToString(truckspace));	//truck.space
	   	System.out.println("The total value of boxes in the truck is " + totalValue);
	   	System.out.println("T: " + counterOfT + ", L: " + counterOfL + ", P: " + counterOfP);
	}
}