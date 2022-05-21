import processing.core.PApplet;

public class Sketch extends PApplet {
    
 /**
  * A program Sketch.java that is made up of 10 by 10 white squares.
  * The squares are all seperated by a margin.
  * If the user presses a square, the square and it's surrounding 4 squares will turn green.
  * If the square is already green, it will turn white.
  * After each click, the program will print out how many squares are green.
  * After each click, the program will determine the mouse coordinates and grid coordinates.
  * The program prints out how many cells are selected on each row and column.
  * The program prints out how many continuous selected cells there are on each row.
  * 
  * @author: D. Gu
  */

  // Set global variables
  int[][] intGrid = new int[10][10];
  int CELL_WIDTH = 20;
  int CELL_HEIGHT = 20;
	int MARGIN = 5;
  int ROW_COUNT = 10;
  int COLUMN_COUNT = 10;
  int SCREEN_WIDTH = (ROW_COUNT * CELL_WIDTH) + ((ROW_COUNT + 1) * MARGIN);
  int SCREEN_HEIGHT = (COLUMN_COUNT * CELL_HEIGHT) + ((COLUMN_COUNT + 1) * MARGIN);
  int intCount = 0;
  int[] intRows = new int[10];
  int[] intColumns = new int[10];
  boolean[][] isSelected = new boolean[10][10];
  boolean[][] isSelect = new boolean[10][10];
  int intContinue = 1;

  /**
   * Sets the size of the call
   * 
   * @param  nothing
   * @return nothing
   * 
   */
  public void settings() {
    size(SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  /**
   * Sets the size of the call and initializes the number of selected cells on rows/columns as 0
   * 
   * @param  nothing
   * @return nothing
   * 
   */
  public void setup() {
    background(0);
    for (int i = 0; i < 10; i++){
      intRows[i] = 0;
      intColumns[i] = 0;
    }
  }

  /**
   * Draws everything inside the method 60 times per second
   * 
   * @param  nothing
   * @return nothing
   * 
   */
  public void draw() {
    // Draw a 10 by 10 grid seperated evenly by margins
    for (int column = 0; column < COLUMN_COUNT; column++){
      for (int row = 0; row < ROW_COUNT; row++){
        if (intGrid[row][column] == 1){
          // Set the color of the squares to green if clicked on
          fill(144, 238, 144);
          rect(CELL_WIDTH * column + (MARGIN * (column + 1)), CELL_HEIGHT * row + (MARGIN * (row + 1)), CELL_WIDTH, CELL_HEIGHT);
        }
        else{
          // Set the color of the squares to white
          fill(255);
          rect(CELL_WIDTH * column + (MARGIN * (column + 1)), CELL_HEIGHT * row + (MARGIN * (row + 1)), CELL_WIDTH, CELL_HEIGHT);
        }
      }
    }
  }
  
  /**
   * Runs whenever the user clicks their mouse
   * 
   * @param  nothing
   * @return nothing
   * 
   */
  public void mousePressed(){
    // Print the coordinates of mouseX and mouseY when mouse clicked
    System.out.println("__________________________________________________");
    System.out.print("mouse coordinates: " + "(" + mouseX + ", " + mouseY + "); ");
    for (int column = 0; column < COLUMN_COUNT; column++){
      for (int row = 0; row < ROW_COUNT; row++){
        // If mouse coordinates are inside a cell, print the cell row and column
        if (mouseX > CELL_WIDTH * column + (MARGIN * (column + 1)) && mouseX < (CELL_WIDTH * column + (MARGIN * (column + 1))) + CELL_WIDTH){
          if (mouseY > CELL_HEIGHT * row + (MARGIN * (row + 1)) && mouseY < (CELL_HEIGHT * row + (MARGIN * (row + 1))) + CELL_HEIGHT){
            System.out.println("grid coordinates: " + "(row: " + (row + 1) + ")" + "(column: " + (column + 1) + ")");
            // If the pressed cell is not selected, select it
            if (intGrid[row][column] == 0){
              intGrid[row][column] = 1;
              intCount+=1;
            }
            // If the pressed cell is selected, unselect it
            else {
              intGrid[row][column] = 0;
              intCount-=1;
            }
            // Change the color of the 4 cells surrounding the pressed cell
            // Set restrictions so locations outside the grid will not be affected
            if (row != 9 && intGrid[row + 1][column] == 0){
              intGrid[row + 1][column] = 1;
              intCount+=1;
            }
            else if (row != 9){
              intGrid[row + 1][column] = 0;
              intCount-=1;
            }
            if (row != 0 && intGrid[row - 1][column] == 0){
              intGrid[row - 1][column] = 1;
              intCount+=1;
            }
            else if (row != 0){
              intGrid[row - 1][column] = 0;
              intCount-=1;
            }
            if (column != 9 && intGrid[row][column + 1] == 0){
              intGrid[row][column + 1] = 1;
              intCount+=1;
            }
            else if (column != 9){
              intGrid[row][column + 1] = 0;
              intCount-=1;
            }
            if (column != 0 && intGrid[row][column - 1] == 0){
              intGrid[row][column - 1] = 1;
              intCount+=1;
            }
            else if (column != 0){
              intGrid[row][column - 1] = 0;
              intCount-=1;
            }
          }
        }
      }
    }
    // Print the total number of cells selected
    System.out.println("Total of " + intCount + " cells are selected.");
    for (int i = 0; i < ROW_COUNT; i++){
      for (int a = 0; a < COLUMN_COUNT; a++){
        // Records the number of selected cells on each row
        if (intGrid[i][a] == 1 && isSelected[i][a] == false){
          intRows[i] = intRows[i] + 1;
          isSelected[i][a] = true;
        }
        if (intGrid[i][a] == 0 && isSelected[i][a] == true){
          intRows[i] = intRows[i] - 1;
          isSelected[i][a] = false;
        }
        // Records how many continuous selected cells are in a row
        if (intGrid[i][a] == 1 && intGrid[i][a + 1] == 1){
          intContinue = intContinue + 1;
        }
      }
      // Print out how many cells are selected on each row
      System.out.println("Row " + (i + 1) + " has " + intRows[i] + " cells selected.");
      if (intContinue >= 2){
        System.out.println("There are " + intContinue + " continuous blocks selected on row " + (i + 1));
        intContinue = 1;
      }
    }
    for (int i = 0; i < COLUMN_COUNT; i++){
      for (int a = 0; a < ROW_COUNT; a++){
        // Records the number of selected cells on each column
        if (intGrid[a][i] == 1 && isSelect[a][i] == false){
          intColumns[i] = intColumns[i] + 1;
          isSelect[a][i] = true;
        }
        if (intGrid[a][i] == 0 && isSelect[a][i] == true){
          intColumns[i] = intColumns[i] - 1;
          isSelect[a][i] = false;
        }
      }
      // Prints out how many cells are selected on each column
      System.out.println("Column " + (i + 1) + " has " + intColumns[i] + " cells selected.");
    }
  }
}


