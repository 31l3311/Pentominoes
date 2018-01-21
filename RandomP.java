/**
* This is the Random algorithm for pentominoes for the knapsack problem
*
* @version 1
*/

import java.util.Random;
import java.util.Arrays;

public class RandomP{

  	private static int[][][] truckspace = new int[5][8][33];
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


  	public RandomP(int[][][][] piece)
  	{
    	this.piece = piece;
    	//this.truck = truck;
  	}


  	public static boolean putPiece(int type, int d, int h, int w)
	{
			//put piece is divided to two parts. the first part check if it's possible to place it on board. if not, return false.
			// the second part put the piece on board
			//*type = 0-60
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
  
	public static void placePen(int type, int d, int h, int w)
	{
		for (int i = 0; i < piece[type].length ; i ++)
			for (int j = 0; j < piece[type][0].length ; j ++)
				for (int k = 0; k < piece[type][0][0].length ; k ++)
					if(piece[type][i][j][k]!=0)
						truckspace[d+i][h+j][w+k] = piece[type][i][j][k];
	}


  	public static void solve()
  	{
  		for(int i=0; i<piece.length; i++)
	      	for(int x = 0; x < truckspace.length; x++)       // is the coordinate still in the truck?
	        	for(int y = 0; y < truckspace[0].length; y++)
	          		for(int z = 0; z < truckspace[0][0].length; z++)
	            //if(checkAmount()== true)               // checking if that box is stil available
	              //for(int r = 0; r < box.rotations; r++)
	              //{  // for a rotation
	                	if(putPiece(i, x, y, z))
	            			placePen(i, x, y, z);
	     		 	//}
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
	   //System.out.println("The total value of boxes in the truck is " + truck.totalValue);
	}
}