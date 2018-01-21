/**
* This is the greedy algorithm for pentominoes for the knapsack problem
*
* @version 1
*/

import java.util.Arrays;

public class GreedyP
{

  	private static int[][][] truckspace = new int[5][8][33];
  	private static int totalValue = 0;
  	private static int amountOfP = 100;
  	private static int amountOfL = 50;
	private static int amountOfT = 160;
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

  	public GreedyP(int[][][][] piece)
  	{
    	this.piece = piece;
  	}

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
     			totalValue = totalValue + 4;
     			return true;
     		}
     		return false;
     	}
    	else //(piece[0][0][0]== 3 || piece[piece.length-1][piece[0].length-1][piece[0][0].length-1]==3)
    	{
     		if(counterOfT < amountOfT)
     		{
     			counterOfT++;
     			totalValue = totalValue + 5;
     			return true;
     		}
     		return false;
     	}
  	}

  	public static boolean putPiece(int type, int d, int h, int w)
	{
		//check if it's possible to place it on board. if not, return false.
		//*type = 0-60
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
	            		if(putPiece(i, x, y, z))
	                		if(checkAmount(piece[i])== true) // checking if that pentomino is stil available
	            				placePen(i, x, y, z);
	}

	public static void main(String[] args)
	{
	   	//Truck truck = new Truck(33, 8, 5);
	   	GreedyP algorithm = new GreedyP(piece);

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