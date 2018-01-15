import java.util.Arrays;
import java.util.Scanner;
public class PentoCargo{
  private static int width = 33;
  private static int height = 8;
  private static int depth = 5;
  private static int numUsed = 0;
  private static int[][][] board = new int[width][height][depth];
  private static int numOfPieces;
  private static int nextRow, nextCol;
  //private static final boolean[] usable = new boolean[13];
  //we need 13 because we're not using the index 0 one. we will be using 1-12

  private  static final int[][] pieces = {

     { 5, 0,1,0,2,1,1,2,1 },    // T with every rotation (#12 roations)
     { 5, 1,-2,1,-1,1,0,2,0 },
     { 5, 1,0,2,-1,2,0,2,1 },
     { 5, 1,0,1,1,1,2,2,0 },

     { 8, 1,0,1,1,1,2,1,3 },    // L with every rotation (#16 rotations)
     { 8, 1,0,2,0,3,-1,3,0 },
     { 8, 0,1,0,2,0,3,1,3 },
     { 8, 0,1,1,0,2,0,3,0 },
     { 8, 0,1,1,1,2,1,3,1 },
     { 8, 0,1,0,2,0,3,1,0 },
     { 8, 1,0,2,0,3,0,3,1 },
     { 8, 1,-3,1,-2,1,-1,1,0 },

     { 12, 0,1,1,0,1,1,2,1 },   //  P with every rotation (#16 rotations)
     { 12, 0,1,0,2,1,0,1,1 },
     { 12, 1,0,1,1,2,0,2,1 },
     { 12, 0,1,1,-1,1,0,1,1 },
     { 12, 0,1,1,0,1,1,1,2 },
     { 12, 1,-1,1,0,2,-1,2,0 },
     { 12, 0,1,0,2,1,1,1,2 },
     { 12, 0,1,1,0,1,1,2,0 }
  };
  public static boolean putPiece(int p, int row, int col){

    // checking if it fits
    for (int i = 1; i < 8; i += 2){
    //first check if all the coordinates are on the board to prevent ArraysOutOfBounds
    //if it's on board we can then check the values
      if((row + pieces[p][i])>=height || (col+pieces[p][i+1])>=width || col+pieces[p][i+1] < 0 || row+pieces[p][i] < 0)
        return false;
      if (board[row + pieces[p][i]][col+pieces[p][i+1]] != 0) {
      // one of the block needed is filled already
        return false;
      }
    }

    // equal to placeBox
    //now place the piece to the board
    board[row][col] = pieces[p][0];//change the value of the first block

    for (int i = 1; i < 8; i += 2){
        board[row + pieces[p][i]][col+pieces[p][i+1]] = pieces[p][0];  
        //we assign the piece value to the 4 other empty blocks
    }
    return true;

  }
  public static void removePiece(int p, int row, int col) {
    for (int i = 1; i < 8; i += 2){
      board[row][col] = 0;
      board[row + pieces[p][i]][col+pieces[p][i+1]] = 0;
    }
  }
  public static void printBoard(){
    for(int i=0; i<height; i++){
      for(int j=0; j<width; j++){//convert the numbers to letters
        if(board[i][j]==5)System.out.print("T");
        if(board[i][j]==8)System.out.print("L");
        if(board[i][j]==12)System.out.print("P");
        if(board[i][j]==0)System.out.print(0);

        System.out.print("  ");
      }
      System.out.println();
    }
  }

  public static void solve(int row, int col) {   //recursive procedure that tries to solve the puzzle

    for (int p=0; p<63; p++){

        if ((usable[pieces[p][0]] == true) && putPiece(p, row, col)){  //try piece p
             //a piece has been placed on the board.

          usable[pieces[p][0]] = false;
          numUsed++;

          if (numUsed == numOfPieces) {  //puzzle is solved if used = pieces
            System.out.println("Solution found:");
            printBoard();

            System.exit(0);
          }
          else {//find the next empty block to fill
            for(int i=height-1; i>=0; i--){
              for(int j=width-1; j>=0; j--){
                if (board[i][j]==0){
                  nextRow = i;
                  nextCol = j;
                }
              }
            }
            solve(nextRow, nextCol);  // try to complete the solution
          }
         removePiece(p, row, col);  // backtrack
         numUsed--;
         usable[pieces[p][0]] = true;
       }
    }
  }

  public static void user(){
    Scanner in = new Scanner(System.in);
    int n=0;

    System.out.println("Enter number of pieces");
    numOfPieces = in.nextInt();
    System.out.print("Please enter the letters of the pieces");
    System.out.println(" (copy this if you're using all Pentominoes: I  X  Z  V  T  W  U  L  N  Y  F  P)");
    for(int i=0; i<numOfPieces; i++){
      char x = (in.next()).charAt(0);
      if(x=='I') n=1;
      if(x=='X') n=2;
      if(x=='Z') n=3;
      if(x=='V') n=4;
      if(x=='T') n=5;
      if(x=='W') n=6;
      if(x=='U') n=7;
      if(x=='L') n=8;
      if(x=='N') n=9;
      if(x=='Y') n=10;
      if(x=='F') n=11;
      if(x=='P') n=12;

      usable[n]=true;
    }



  }

  public static void main(String[] args){
    user();

    if(checkBlockEquals()){
      solve(0,0);
    }
    System.out.println("No solution found");
  }
}
