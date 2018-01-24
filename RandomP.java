/**
* This is the Random algorithm for pentominoes for the knapsack problem
*
* @version 1
*/

import java.util.Random;
import java.util.Arrays;

public class RandomP{

  	private static int[][][] truckspace = new int[33][5][8];
  	private static int totalValue = 0;
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
	public RandomP(int[][][][] piece)
  	{
    	this.piece = piece;
  	}

 	/**
    *checks if the pentomino fits in the empty space
    *
    *@param type: type of pentomino used
    *@param d, h, w: dimensions of the pentomino
    */
  	public static boolean checkFitOfP(int type, int d, int h, int w)
	{
	    for (int i = 0; i < piece[type].length ; i ++)
	    {
			for (int j = 0; j < piece[type][0].length ; j ++)
				for (int k = 0; k < piece[type][0][0].length ; k ++)
					if(piece[type][i][j][k]!=0)
					{
						if(i+d>=truckspace.length||j+h>=truckspace[0].length||k+w>=truckspace[0][0].length)
		        			return false;
						if(truckspace[i+d][j+h][k+w] !=0)
							return false;
					}
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
		if(piece[type][0][0][0]== 1 || piece[type][piece[type].length-1][piece[type][0].length-1][piece[type][0][0].length-1]==1)
      		totalValue = totalValue + 3;
    	else if(piece[type][0][0][0]== 2 || piece[type][piece[type].length-1][piece[type][0].length-1][piece[type][0][0].length-1]==2)
      		totalValue = totalValue + 4;
 		else //(piece[0][0][0]== 3 || piece[piece[type].length-1][piece[0].length-1][piece[0][0].length-1]==3)
      		totalValue = totalValue + 5;
		for (int i = 0; i < piece[type].length ; i ++)
			for (int j = 0; j < piece[type][0].length ; j ++)
				for (int k = 0; k < piece[type][0][0].length ; k ++)
					if(piece[type][i][j][k]!=0)
					{
						truckspace[d+i][h+j][w+k] = piece[type][i][j][k];

					}
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
	            			putP(i, x, y, z);
	}

	public static void main(String[] args)
	{

	    for (int i = 0; i < piece.length; i++)
	    {
	      int s = (int)(Math.random()* (piece.length -i));

	      int[][][] temp = piece[s];
	      piece[s] = piece[i];
	      piece[i] = temp;
	    }

	   	RandomP algorithm = new RandomP(piece);

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
	        	{
	          		System.out.print(truckspace[x][y][z] + " ");
	        	}
	        System.out.println();
	      	}
	    System.out.println();
	    }
	    //System.out.println(Arrays.deepToString(truckspace));	//truck.space
	   System.out.println("The total value of pentominoes in the truck is " + totalValue);
	}
}
