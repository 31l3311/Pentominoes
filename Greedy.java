import java.util.Arrays;

public class Greedy{
public Box[] boxes;
public Truck truck;

  public Greedy(Box[] boxes, Truck truck){
    this.boxes = doInsertionSort(boxes, "density");
    this.truck = truck;
  }

  public Box[] doInsertionSort(Box[] input, String key){
      Box temp;
      for (int i = 1; i < input.length; i++) {
          for(int j = i ; j > 0 ; j--){
              if(input[j].density > input[j-1].density){
                  temp = input[j];
                  input[j] = input[j-1];
                  input[j-1] = temp;
              }
          }
      }
      return input;
    }

  public boolean checkAmount(Box box){
    if(box.amountOfBoxes != 0)
      return true;
    else
      return false;
  }

  public void solve(){
    for(Box box: this.boxes) {    //for each element in the array boxes (i.e. for each box)
      for(int x = 0; x < this.truck.width; x++){    //is the coordinate still in the truck?
        for(int y = 0; y < this.truck.height; y++){
          for(int z = 0; z < this.truck.depth; z++){
            for(int r = 0; r < box.rotations; r++){ //for a rotation we check if it fits
              if(checkAmount(box)== true)           // checking if that box is stil available
                if(this.truck.fitIn(box, x, y, z)){
                  this.truck.placeBox(box, x, y, z);
                }else{
                  box.rotate("x");                    // if not we try next rotation
                  if(r % 4 == 0) box.rotate("y");
                  if(r % 16 == 0) box.rotate("z");
                }
            }
          }
        }
      }
    }
  }

  public static void main(String[] args){
    Box[] boxes = {new Box("A", 1, 0), new Box("B", 1, 0), new Box("C", 1, 100)};
    Truck truck = new Truck(33, 8, 5);
    Greedy algorithm = new Greedy(boxes, truck);
    for(Box b: boxes){
      System.out.println(b.density);
    }
    algorithm.solve();
    System.out.println(Arrays.deepToString(truck.space));
    System.out.println(truck.totalValue);
  }
}

// only type A boxes:               totalValue 192, #boxes: 64
// 10 type A boxes, unlimited B, C: totalValue 204
// 0 type A boxes, unlimited B, C:  totalValue 214
// only C: bad
// only B: #boxes: 54