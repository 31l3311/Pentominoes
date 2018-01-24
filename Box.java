/**
* This is the Box class for the knapsack problem
*
* @version 2
*/

public class Box
{
  public String type;
  public int value;
  public int amountOfBoxes;

  public int width;
  public int height;
  public int depth;

  public int width_a = 2;
  public int height_a= 2;
  public int depth_a= 4;

  public int width_b= 2;
  public int height_b= 3;
  public int depth_b= 4;

  public int width_c= 3;
  public int height_c= 3;
  public int depth_c= 3;

  public int volume;
  public double density;
  public int rotations;


  /**
  *constructor of the class
  *
  *@param type: type of box
  *@param value: value of box
  *@param amountOfBoxes: amount of boxes given 
  */
  public Box(String type, int value, int amountOfBoxes)
  {
    this.type = type;
    this.value = value;
    this.amountOfBoxes = amountOfBoxes;
    if (type.equals("A"))
    {
      this.width = width_a;
      this.height = height_a;
      this.depth = depth_a;
    }
    if (type.equals("B"))
    {
      this.width = width_b;
      this.height = height_b;
      this.depth = depth_b;
    }
    if (type.equals("C"))
    {
      this.width = width_c;
      this.height = height_c;
      this.depth = depth_c;
    }
    
    this.volume = this.width * this.height * this.depth;
    this.density = (this.value * 100) / this.volume;
    this.rotations = 64;
  }

  /**
  *rotation method
  *
  *@param direction: defines in which direction the box will be rotated
  */
  public void rotate(String direction)
  {
    if(direction.equals("x"))
    {
      int t = this.width;
      this.width = this.height;
      this.height = t;
    }
    else if(direction.equals("y"))
    {
      int t = this.depth;
      this.depth = this.height;
      this.height = t;
    } 
    else if(direction.equals("z"))
    {
      int t = this.width;
      this.width = this.depth;
      this.depth = t;
    }
  }
}