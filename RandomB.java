//package project3;

import java.util.Random;
import java.util.Arrays;

public class RandomB{

	public Box[] boxes;
  	public Truck truck;

  	public RandomB(Box[] boxes, Truck truck)
  	{
    	this.boxes = boxes;
    	this.truck = truck;
  	}

	public boolean checkAmount(Box box)
 	{
   		if(box.amountOfBoxes != 0)
     		return true;
    	else
     		return false;
  	}


  	public void solve()
  	{
  		for(Box box: this.boxes)
  		{
	      for(int x = 0; x < this.truck.width; x++)       // is the coordinate still in the truck?
	        for(int y = 0; y < this.truck.height; y++)
	          for(int z = 0; z < this.truck.depth; z++)
	            if(checkAmount(box)== true)               // checking if that box is stil available
	              for(int r = 0; r < box.rotations; r++)
	              {  // for a rotation
	                if(this.truck.fitIn(box, x, y, z))
	                {
	                  this.truck.placeBox(box, x, y, z);
	                }
	                else
	                {
	                  box.rotate("x");                    // if not we try next rotation
	                  if(r % 4 == 0) box.rotate("y");
	                  if(r % 16 == 0) box.rotate("z");
	                }
	    		  }
		}
	}

	public static void main(String[] args)
	{
	    Box[] boxes = {new Box("A", 3, 100), new Box("B", 4, 100), new Box("C", 5, 100)};

	    // shuffles the array
	    for (int i = 0; i < boxes.length; i++)
	    {
	      int s = (int)(Math.random()* (boxes.length -i));
	  
	      Box temp = boxes[s];
	      boxes[s] = boxes[i];
	      boxes[i] = temp;
	    }

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
	  }

}