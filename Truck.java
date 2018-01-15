public class Truck{
  public static int width;
  public static int height;
  public static int depth;
  public static int[][][] space;
  public static int totalValue;

  public Truck(int width, int height, int depth){
    this.width = width;
    this.height = height;
    this.depth = depth;
    this.space = new int[width][height][depth];
    this.totalValue = 0;
  }

  public boolean fitIn(Box box, int w, int h, int d){
    for(int x = w; x < w + box.width; x++)
      for(int y = h; y < h + box.height; y++)
        for(int z = d; z < d + box.depth; z++){
          if((x <= this.width - 1) && (y <= this.height - 1) && (z <= this.depth - 1)){
            if(this.space[x][y][z] != 0) { return false; }
          } else{ return false; }
        }
    return true;
  }

  public void placeBox(Box box, int w, int h, int d){
    for(int x = w; x < w + box.width; x++)
      for(int y = h; y < h + box.height; y++)
        for(int z = d; z < d + box.depth; z++){
          this.space[x][y][z] = box.value;
        }
    this.totalValue += box.value;
    box.amountOfBoxes--;
  }
}
