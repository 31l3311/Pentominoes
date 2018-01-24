/**
* This is the Random algorithm for boxes for the knapsack problem
*
* @version 1
*/

import java.util.Random;
import java.util.Arrays;

public class RandomB
{

	public Box[] boxes;
  	public Truck truck;

  	/**
  	*constructor of the class
  	*
  	*@param boxes: the box used
  	*@param truck: the cargo- space where the boxes go
  	*/
  	public RandomB(Box[] boxes, Truck truck)
  	{
    	this.boxes = boxes;
    	this.truck = truck;
  	}

	/**
  	*the method which decides if and in which orientation the boxes are put into the truck
  	*
  	*/
  	public void solve()
  	{
  		for(Box box: this.boxes)
  		{
  			double n = Math.random();
  			if(n>0 && n<1/4)
  				box.rotate("x");
  			else if(n>1/4 && n<2/4)
  				box.rotate("y");
  			else if(n>2/4 && n<3/4)
  				box.rotate("z");
  			else {}
  			System.out.println(n);
	      	for(int x = 0; x < this.truck.width; x++)       // is the coordinate still in the truck?
	        	for(int y = 0; y < this.truck.height; y++)
	          		for(int z = 0; z < this.truck.depth; z++)
						for(int r = 0; r < box.rotations; r++)	// for a rotation
	                		if(this.truck.fitIn(box, x, y, z))
	                		{
	                  			this.truck.placeBox(box, x, y, z);
	                		}
		}
	}

	public static void main(String[] args)
	{
	    Box[] boxes = {new Box("A", 1, 1000), new Box("B", 1, 1000), new Box("C", 1, 1000)};

	    // shuffles the array
	    for (int i = 0; i < boxes.length; i++)
	    {
	      	int s = (int)(Math.random()* (boxes.length -i));
	  
	      	Box temp = boxes[s];
	      	boxes[s] = boxes[i];
	      	boxes[i] = temp;
	    }


	    Truck truck = new Truck(33, 5, 8);
	    RandomB algorithm = new RandomB(boxes, truck);


	    /**
  		* prints out the density and the truck in 'slices' to visualize the cargo- space better
  		*
  		*/
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
	}

}