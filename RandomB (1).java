/**
* This is the Random algorithm for boxes for the knapsack problem
*
* @version 1
*/

import java.util.Random;
import java.util.Arrays;

public class RandomB
{

	public static Box[] boxes;
  	public Truck truck;

  	public RandomB(Box[] boxes, Truck truck)
  	{
    	this.boxes = boxes;
    	this.truck = truck;
  	}

  	public void solve()
  	{
			Box box = boxes[0];
	  	for(int x = 0; x < this.truck.width; x++)       // is the coordinate still in the truck?
	    	for(int y = 0; y < this.truck.height; y++)
	      	for(int z = 0; z < this.truck.depth; z++)
					{
						int counter=0;
						while(this.truck.fitIn(box, x, y, z)==false && counter<10)
						{
							shuffleBoxOrder();
							box = boxes[0];
							box = randomRotate(box);
							counter++;
						}

						if(this.truck.fitIn(box, x, y, z))
							this.truck.placeBox(box, x, y, z);
						}

		}


	public static void shuffleBoxOrder()
	{
		for (int i = 0; i < boxes.length; i++)
		{
				int s = (int)(Math.random()* (boxes.length -i));

				Box temp = boxes[s];
				boxes[s] = boxes[i];
				boxes[i] = temp;
		}
	}
	public static Box randomRotate(Box box)
	{
		int[] n = new int[3];
		for(int i=0; i<3; i++){
			n[i]=(int)(Math.random()*2);
		}
		if(n[0]==1)box.rotate("x");
		if(n[1]==1)box.rotate("y");
		if(n[2]==1)box.rotate("z");

		//System.out.println(Arrays.toString(n));

		return box;
	}
	public static void main(String[] args)
	{
	    Box[] boxes = {new Box("A", 3, 100), new Box("B", 4, 100), new Box("C", 5, 100)};

	    // shuffles the array


	    Truck truck = new Truck(33, 8, 5);
	    RandomB algorithm = new RandomB(boxes, truck);

	    System.out.println();
	    for(Box b: boxes)
	    {
	      System.out.println("Density of box " + b.type +": " + b.density);
	    }
	    System.out.println();
	    algorithm.solve();

	    for(int x = 0; x < truck.space.length; x++)
	    {
	      	if(x % 2==0)
	      	{
	        	System.out.println((x/2) + " m");
	        	System.out.println();
	      	}
	      	for(int y = 0; y < truck.space[0].length; y++)
	      	{
	        	for(int z = 0; z < truck.space[0][0].length; z++)
	        	{
	          		System.out.print(truck.space[x][y][z] + " ");
	        	}
	        	System.out.println();
	      	}
	    System.out.println();
	    }
	    //System.out.println(Arrays.deepToString(truck.space));
	    System.out.println("The total value of boxes in the truck is " + truck.totalValue);
			System.out.println(boxes[0].type+" "+boxes[1].type+" "+boxes[2].type);
	}

}
