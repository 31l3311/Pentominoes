/**
* This is the greedy algorithm for boxes for the knapsack problem
*
* @version 3
*/

import java.util.Arrays;

public class GreedyB
{
  public Box[] boxes;
  public static Truck truck;

  /**
    *constructor of the class
    *
    *@param boxes: the box used
    *@param truck: the cargo- space where the boxes go
    */
  public GreedyB(Box[] boxes, Truck truck)
  {
    this.boxes = doInsertionSort(boxes, "density");
    this.truck = truck;
  }

  /**
    *sorting method which for each object in array goes from front to back and again from back to front, 
    *because it may still not be in order switches the indexes of the boxes if density of current box > density of next box
    *
    *@param input 
    *@param key
    */
  public Box[] doInsertionSort(Box[] input, String key)
  {
      Box temp;
      for (int i = 1; i < input.length; i++)    
      {    
          for(int j = i ; j > 0 ; j--)          
          {    
              if(input[j].density > input[j-1].density)   
              {    
                  temp = input[j];             
                  input[j] = input[j-1];        
                  input[j-1] = temp;
              }
         }
      }
      return input;
  }

  public boolean checkAmount(Box box)
  {
    if(box.amountOfBoxes != 0)
      return true;
    else
      return false;
  }

  /**
    *the method which decides if and in which orientation the boxes are put into the truck
    *
    */
  public void solve()
  {
    for(Box box: this.boxes)   // for each element in the array boxes (i.e. for each box)
    { 
      //box.rotate("z"); 
      //box.rotate("x");
      for(int x = 0; x < this.truck.width; x++)
        for(int y = 0; y < this.truck.height; y++)
          for(int z = 0; z < this.truck.depth; z++)
            if(checkAmount(box)== true)               // checking if that box is still available
              for(int r = 0; r < box.rotations; r++)
              {
               // for a rotation

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
    Box[] boxes = {new Box("A", 3, 0), new Box("B", 4, 1000), new Box("C", 5, 1000)};
    //Truck truck = new Truck(33, 8, 5);
    //Truck truck = new Truck(33, 5, 8);
    //Truck truck = new Truck(8, 33, 5);
    Truck truck = new Truck(8, 5, 33);
    //Truck truck = new Truck(5, 8, 33);
    //Truck truck = new Truck(5, 33, 8);

    GreedyB algorithm = new GreedyB(boxes, truck);

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

    for(int x=0; x<truck.space.length; x++)
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