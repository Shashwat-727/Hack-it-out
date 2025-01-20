/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


 import java.util.LinkedHashSet;
 import java.util.Set;
 
 /**
  *
  * @author shashwat Mishra
  */
 abstract public class SPYAlgo{
     public String[] Spy_moveN(String Direction, String[][] matrix, int current_row, int current_column) {
         int row = current_row;
         int column = current_column;
         int x = row;
         int bomb_defused = 0;
         int trigger_track = 0;
 
         Set<String> moves = new LinkedHashSet<>();
         while (x != 0) {
             int bomb_check_counter = 0;
             if ("x".equals(matrix[row - 1][column])) {
                 matrix[row][column] = "x";
                 matrix[row - 1][column] = "SPY";
                 moves.add("[" + (row - 1) + "," + column + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row - 1) + "," + (column) + "]" + "Cell");
                 x = row - 1;
                 row = row - 1;
                 trigger_track = 0;
             } else if ("x".equals(matrix[row - 1][column - 1])) {
                 //Overriding the current spys position
                 matrix[row][column] = "x";
                 matrix[row - 1][column - 1] = "SPY";
                 moves.add("[" + (row - 1) + "," + (column -1)+ "]");
 //                    System.out.println("SPY Moved To " + "[" + (row - 1) + "," + (column - 1) + "]" + "Cell");
                 x = row - 1;
                 row = row - 1;
                 column = column - 1;
                 trigger_track = 0;
             } else if ("x".equals(matrix[row - 1][column + 1])) {
                 matrix[row][column] = "x";
                 matrix[row - 1][column + 1] = "SPY";
                 moves.add("[" + (row - 1) + "," + (column+1) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row - 1) + "," + (column + 1) + "]" + "Cell");
                 x = row - 1;
                 row = row - 1;
                 column = column + 1;
                 trigger_track = 0;
             } else if ("x".equals(matrix[row][column - 1])) {
                 matrix[row][column] = "x";
                 matrix[row][column - 1] = "SPY";
                 moves.add("[" + (row) + "," + (column-1) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row) + "," + (column - 1) + "]" + "Cell");
                 x = row;
                 column = column - 1;
                 trigger_track += 1;
             } else if ("x".equals(matrix[row][column + 1])) {
                 matrix[row][column] = "x";
                 matrix[row][column + 1] = "SPY";
                 moves.add("[" + (row) + "," + (column+1) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row) + "," + (column + 1) + "]" + "Cell");
                 x = row;
                 column = column + 1;
                 trigger_track += 1;
             } else {
                 bomb_check_counter += 1;
             }
             if (bomb_check_counter == 1 && row != 0) {
                 matrix[row][column] = "x";
 //                    System.out.println("SPY Defusing the bomb..");
                 try {
                     if (bomb_defused == 3) {
                         Thread.sleep(3500);
                     } else {
                         Thread.sleep(2000);
                     }
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 matrix[row - 1][column] = "SPY";
                 moves.add("[" + (row - 1) + "," + column + "]");
                 x = row - 1;
                 row = row - 1;
 //                    System.out.println("SPY Moved To " + "[" + (row - 1) + "," + (column) + "]" + "Cell");
                 bomb_defused += 1;
             } else if (trigger_track == 3 && row != 0) {
                 matrix[row][column] = "x";
 //                    System.out.println("SPY Defusing the bomb..");
                 try {
                     Thread.sleep(2000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 matrix[row - 1][column] = "SPY";
                 moves.add("[" + (row - 1) + "," + column + "]");
                 x = row - 1;
                 row = row - 1;
 //                    System.out.println("SPY Moved To " + "[" + (row - 1) + "," + (column) + "]" + "Cell");
 //                    System.out.println("Triggered trigger");
                 bomb_defused += 1;
             }
         }
 
         System.out.println("Spy Of " + Direction + " Reached");
         System.out.println("\t\t.");
         System.out.println("\t\t.");
         System.out.println("\t\t.");
         System.out.println("\t\t.");
 
         return moves.toArray(new String[0]);
     }
 
     public String[] Spy_MoveS(String Direction, String[][] matrix, int current_row, int current_column, int while_condition) {
         //        Moving the Spy Towards it's kings palace
         int row = current_row;
         int column = current_column;
         int x = row;
         int bomb_defused = 0;
         int trigger_track = 0;
         Set<String> moves = new LinkedHashSet<>();
         while (x != while_condition) {
             int bomb_check_counter = 0; //indicates that the spy is surrounded by lots of bomb
             //left
             //right
             //center
             //left back
             //right back
             if ("x".equals(matrix[row + 1][column - 1])) {
                 matrix[row][column] = "x";
                 matrix[row + 1][column - 1] = "SPY";
                 moves.add("[" + (row+1) + "," + (column-1) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row + 1) + "," + (column - 1) + "]" + "Cell");
                 x = row + 1;
                 row = row + 1;
                 column = column - 1;
                 trigger_track = 0;
             } else if ("x".equals(matrix[row + 1][column + 1])) {
                 //Overriding the current spys position
                 matrix[row][column] = "x";
                 matrix[row + 1][column + 1] = "SPY";
                 moves.add("[" + (row+1) + "," + (column+1) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row + 1) + "," + (column + 1) + "]" + "Cell");
                 x = row + 1;
                 row = row + 1;
                 column = column + 1;
                 trigger_track = 0;
 
             } else if ("x".equals(matrix[row + 1][column])) {
                 matrix[row][column] = "x";
                 matrix[row + 1][column] = "SPY";
                 moves.add("[" + (row+1) + "," + (column) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row + 1) + "," + (column) + "]" + "Cell");
                 x = row + 1;
                 row = row + 1;
                 trigger_track = 0;
             } else if ("x".equals(matrix[row][column - 1])) {
                 matrix[row][column] = "x";
                 matrix[row][column - 1] = "SPY";
                 moves.add("[" + (row) + "," + (column-1) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row) + "," + (column - 1) + "]" + "Cell");
                 x = row;
                 column = column - 1;
                 trigger_track += 1;
             } else if ("x".equals(matrix[row][column + 1])) {
                 matrix[row][column] = "x";
                 matrix[row][column + 1] = "SPY";
                 moves.add("[" + (row) + "," + (column+1) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row) + "," + (column + 1) + "]" + "Cell");
                 x = row;
                 column = column + 1;
                 trigger_track += 1;
             } else {
                 bomb_check_counter += 1;
             }
             if (bomb_check_counter == 1 && row != while_condition) {
                 matrix[row][column] = "x";
 //                    System.out.println("SPY Defusing the bomb..");
                 try {
                     if (bomb_defused == 3) {
                         Thread.sleep(3500);
                     } else {
                         Thread.sleep(2000);
                     }
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 matrix[row + 1][column] = "SPY";
                 moves.add("[" + (row+1) + "," + (column) + "]");
                 x = row + 1;
                 row = row + 1;
 //                    System.out.println("SPY Moved To " + "[" + (row + 1) + "," + (column) + "]" + "Cell");
                 bomb_defused += 1;
             } else if (trigger_track == 3 && row != while_condition) {
                 matrix[row][column] = "x";
 //                    System.out.println("SPY Defusing the bomb..");
                 try {
                     Thread.sleep(2000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 matrix[row + 1][column] = "SPY";
                 moves.add("[" + (row+1) + "," + (column) + "]");
                 x = row + 1;
                 row = row + 1;
 //                    System.out.println("SPY Moved To " + "[" + (row + 1) + "," + (column) + "]" + "Cell");
 //                    System.out.println("Triggered trigger");
                 bomb_defused += 1;
             }
         }
         System.out.println("Spy Of " + Direction + " Reached");
         System.out.println("\t\t.");
         System.out.println("\t\t.");
         System.out.println("\t\t.");
         System.out.println("\t\t.");
         return moves.toArray(new String[0]);
     }
 
     public String[] Spy_MoveE(String Direction, String[][] matrix, int current_row, int current_column) {
         //            Moving the Spy Towards it's kings palace
         int row = current_row;
         int column = current_column;
         int x = column;
         int bomb_defused = 0;
         int trigger_track = 0;
         Set<String> moves = new LinkedHashSet<>();
         while (x != 0) {
             int bomb_check_counter = 0; //indicates that the spy is surrounded by lots of bomb
             //center
             //right
             //left
             //left back
             //right back
             if ("x".equals(matrix[row][column - 1])) {
                 matrix[row][column] = "x";
                 matrix[row][column - 1] = "SPY";
                 moves.add("[" + (row) + "," + (column-1) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row) + "," + (column - 1) + "]" + "Cell");
                 x = column - 1;
                 column = column - 1;
                 trigger_track = 0;
             } else if ("x".equals(matrix[row - 1][column - 1])) {
                 //Overriding the current spys position
                 matrix[row][column] = "x";
                 matrix[row - 1][column - 1] = "SPY";
                 moves.add("[" + (row-1) + "," + (column-1) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row - 1) + "," + (column - 1) + "]" + "Cell");
                 x = column - 1;
                 row = row - 1;
                 column = column - 1;
                 trigger_track = 0;
 
             } else if ("x".equals(matrix[row + 1][column - 1])) {
                 matrix[row][column] = "x";
                 matrix[row + 1][column - 1] = "SPY";
                 moves.add("[" + (row+1) + "," + (column-1) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row + 1) + "," + (column - 1) + "]" + "Cell");
                 x = column - 1;
                 row = row + 1;
                 column = column - 1;
                 trigger_track = 0;
             } else if ("x".equals(matrix[row + 1][column])) {
                 matrix[row][column] = "x";
                 matrix[row + 1][column] = "SPY";
                 moves.add("[" + (row+1) + "," + (column) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row + 1) + "," + (column) + "]" + "Cell");
                 x = column;
                 row = row + 1;
                 trigger_track += 1;
             } else if ("x".equals(matrix[row - 1][column])) {
                 matrix[row][column] = "x";
                 matrix[row - 1][column] = "SPY";
                 moves.add("[" + (row-1) + "," + (column) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row - 1) + "," + (column) + "]" + "Cell");
                 x = column;
                 row = row - 1;
                 trigger_track += 1;
             } else {
                 bomb_check_counter += 1;
             }
             if (bomb_check_counter == 1 && column != 0) {
                 matrix[row][column] = "x";
 //                    System.out.println("SPY Defusing the bomb..");
                 try {
                     if (bomb_defused == 3) {
                         Thread.sleep(3500);
                     } else {
                         Thread.sleep(2000);
                     }
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 matrix[row][column - 1] = "SPY";
                 moves.add("[" + (row) + "," + (column-1) + "]");
                 x = column - 1;
                 row = row;
                 column = column - 1;
 //                    System.out.println("SPY Moved To " + "[" + (row) + "," + (column - 1) + "]" + "Cell");
                 bomb_defused += 1;
             } else if (trigger_track == 3 && column != 0) {
                 matrix[row][column] = "x";
 //                    System.out.println("SPY Defusing the bomb..");
                 try {
                     Thread.sleep(2000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 matrix[row][column - 1] = "SPY";
                 moves.add("[" + (row) + "," + (column-1) + "]");
                 x = column - 1;
                 column = column - 1;
 //                    System.out.println("SPY Moved To " + "[" + (row) + "," + (column - 1) + "]" + "Cell");
 //                    System.out.println("Triggered trigger");
                 bomb_defused += 1;
             }
         }
         System.out.println("Spy Of " + Direction + " Reached");
         System.out.println("\t\t.");
         System.out.println("\t\t.");
         System.out.println("\t\t.");
         System.out.println("\t\t.");
         return moves.toArray(new String[0]);
     }
 
     public String[] Spy_MoveW(String Direction, String[][] matrix, int current_row, int current_column, int while_condition) {
         //Moving the Spy Towards it's kings palace
         int row = current_row;
         int column = current_column;
         int x = column;
         int bomb_defused = 0;
         int trigger_track = 0;
         Set<String> moves = new LinkedHashSet<>();
         while (x != while_condition) {
             int bomb_check_counter = 0; //indicates that the spy is surrounded by lots of bomb
             //left
             //center
             //right
             //left back
             //right back
             if ("x".equals(matrix[row - 1][column + 1])) {
                 matrix[row][column] = "x";
                 matrix[row - 1][column + 1] = "SPY";
                 moves.add("[" + (row-1) + "," + (column+1) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row - 1) + "," + (column + 1) + "]" + "Cell");
                 x = column + 1;
                 row = row - 1;
                 column = column + 1;
                 trigger_track = 0;
             } else if ("x".equals(matrix[row][column + 1])) {
                 //Overriding the current spys position
                 matrix[row][column] = "x";
                 matrix[row][column + 1] = "SPY";
                 moves.add("[" + (row) + "," + (column+1) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row) + "," + (column + 1) + "]" + "Cell");
                 x = column + 1;
                 column = column + 1;
                 trigger_track = 0;
 
             } else if ("x".equals(matrix[row + 1][column + 1])) {
                 matrix[row][column] = "x";
                 matrix[row + 1][column + 1] = "SPY";
                 moves.add("[" + (row+1) + "," + (column+1) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row + 1) + "," + (column + 1) + "]" + "Cell");
                 x = column + 1;
                 row = row + 1;
                 column = column + 1;
                 trigger_track = 0;
             } else if ("x".equals(matrix[row - 1][column])) {
                 matrix[row][column] = "x";
                 matrix[row - 1][column] = "SPY";
                 moves.add("[" + (row-1) + "," + (column) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row - 1) + "," + (column) + "]" + "Cell");
                 x = column;
                 row = row - 1;
                 trigger_track += 1;
             } else if ("x".equals(matrix[row + 1][column])) {
                 matrix[row][column] = "x";
                 matrix[row + 1][column] = "SPY";
                 moves.add("[" + (row+1) + "," + (column) + "]");
 //                    System.out.println("SPY Moved To " + "[" + (row + 1) + "," + (column) + "]" + "Cell");
                 x = column;
                 row = row + 1;
                 trigger_track += 1;
             } else {
                 bomb_check_counter += 1;
             }
             if (bomb_check_counter == 1 && column != while_condition) {
                 matrix[row][column] = "x";
 //                    System.out.println("SPY Defusing the bomb..");
                 try {
                     if (bomb_defused == 3) {
                         Thread.sleep(3500);
                     } else {
                         Thread.sleep(2000);
                     }
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 matrix[row][column + 1] = "SPY";
                 moves.add("[" + (row) + "," + (column+1) + "]");
                 x = column + 1;
                 row = row;
                 column = column + 1;
 //                    System.out.println("SPY Moved To " + "[" + (row) + "," + (column + 1) + "]" + "Cell");
                 bomb_defused += 1;
             } else if (trigger_track == 3 && column != while_condition) {
                 matrix[row][column] = "x";
 //                    System.out.println("SPY Defusing the bomb..");
                 try {
                     Thread.sleep(2000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 matrix[row][column + 1] = "SPY";
                 moves.add("[" + (row) + "," + (column+1) + "]");
                 x = column + 1;
                 column = column + 1;
 //                    System.out.println("SPY Moved To " + "[" + (row) + "," + (column + 1) + "]" + "Cell");
 //                    System.out.println("Triggered trigger");
                 bomb_defused += 1;
             }
         }
         System.out.println("Spy Of " + Direction + " Reached");
         System.out.println("\t\t.");
         System.out.println("\t\t.");
         System.out.println("\t\t.");
         System.out.println("\t\t.");
         return moves.toArray(new String[0]);
     }
 }