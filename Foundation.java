/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



 import java.util.Arrays;
 import java.util.Random;

 
 /**
  *
  * @author shashwat Mishra
  */
 //King Palace --- King A  --position center
 //Their are 4 Kings At all side of the King A that wants to capture the Palace of King A
 //King E1 - North
 //King E2 - South
 //King E3 - East
 //King E4 - West
 //Each King on the North, South, East, West has their Spy's which will go to the King A's Palace in such a manner that they don't detonate the bomb on the
 //way coz tiles have bomb inside them and spy's will note the location of tiles which don't have bomb in it
 //Will use Depth first Search algorithm if possible inorder to check wether a tile has bomb in it or not the spy will use a magnate to check if a tile
 //has bomb in it or not -(Spy's Algorithm)
 //if A spy leaves the King A's Palace the bomb position will be shifted
 //Therefore 4 Spy's
 //King E1 - Spy E1 - North
 //King E2 - Spy E2 - South
 //King E3 - Spy E3 - East
 //King E4 - Spy E4 - West
 //After the spy reaches the King A's Palace a Security alarm is triggered such that it changes the arrangment of the bomb under each tile that means the
 //Bombs can be aligned in Horizontal manner, vertical Manner, and in diagonal manner and randomly (this part of how the bombs will be setted will be automatic
 //ally selectd by the program which the user don't know and each side their will be different orientation of how the landmines will be placed)-- Means
 //(DEVELOPING A ALGORITHM TO SET BOMBS) Each tiles will have B under them and tiles which will blow up the solider will have small "b"
 //After the Spy comes back and provide coordinates on tiles which has bomb on it
 //We use that coordinate to build a algorithm
 //while the spy search's for tiles which has bomb in it the terminal should show that King E1 is waiting , King E2 is waiting...etc
 //Options of on how the King E1 and all the Other King's should move ( Horizontal mannner , Vertical Manner, and in Diagonal and random Manner)
 //Total Algo's
 //Spy Going - (noting tiles)
 //Spy Coming - safely(just the message)
 //King's Counter Bomb Path - 4 algo
 
 
 public class Foundation extends SPYAlgo implements King {
 
 
     //Matrix
     static String[][] matrix = new String[50][50];
     static int n = 50;
     static int[] top_leftdiagonal_top_limit = new int[2];
     static int[] top_leftdiagonal_low_limit = {0, 0};
     static int[] bottom_rightdiagonal_low_limit = new int[2];
     static int[] bottom_rightdiagonal_top_limit = {n - 1, n - 1};
     String [] movesSpy1;
     String[] movesSpy2;
     String[] movesSpy3;
     String[] movesSpy4;
 //initialising matrix
     public void Diagonal() {
 
         //Initializing
         for(int k=0;k<n;k++){
             for(int j = 0;j<n;j++){
                 matrix[k][j]="x";
             }
         }
 
         int mid = (n / 2) - 1;
 
         for(int j =0;j<n;j++){
             matrix[j][(n-1)-j]="d";
         }
 
 
         for (int i = 0; i < n; i++) {
             matrix[i][i]="d";
             if (i == (mid - 2)) {
                 top_leftdiagonal_top_limit[0] = i;
                 top_leftdiagonal_top_limit[1] = i;
             }
             if (i == (mid + 3)) {
                 bottom_rightdiagonal_low_limit[0] = i;
                 bottom_rightdiagonal_low_limit[1] = i;
             }
         }
         matrix[mid][mid]="K";
         matrix[mid+1][mid+1]="K";
         matrix[mid][mid+1]="K";
         matrix[mid+1][mid]="K";
     }
 
     public int Randomfunc(int min, int max) {
         Random ran = new Random(System.nanoTime());
         int range = ran.nextInt((max - min) + 1) + min;
         return range;
     }
     //bomb plant before spy leaves matrix
     public void planting(){
         int plantNorth=Randomfunc(75,300);
         int plantEast=Randomfunc(75,300);
         int plantSouth=Randomfunc(75,300);
         int plantWest=Randomfunc(75,300);
         NorthPlanting(plantNorth);
         SouthPlanting(plantSouth);
         EastPlanting(plantEast);
         WestPlanting(plantWest);
         System.out.println("Matrix Initialized...");
         System.out.println("\t\t.");
         System.out.println("\t\t.");
         System.out.println("Bomb has Been Planted..");
     }
 
     public void Display() {
         for (int k = 0; k < n; k++) {
             for (int j = 0; j < n; j++) {
                 System.out.print("   " + matrix[k][j]);
             }
             System.out.println(" ");
         }
     }
 
     public void Display(String clr){
         int mid = (n / 2) - 1;
         if(clr=="Clear"){
             for (int k = 0; k < n; k++) {
                 for (int j = 0; j < n; j++) {
                     matrix[k][j]="x";
                 }
             }
         }
         matrix[mid][mid]="K";
         matrix[mid+1][mid+1]="K";
         matrix[mid][mid+1]="K";
         matrix[mid+1][mid]="K";
     }
 
 
 
     public void NorthPlanting(int numBomb) {
         int counter = 0;
         for (int i = 0; i < numBomb; i++) {
             int row_value = Randomfunc(top_leftdiagonal_low_limit[0], top_leftdiagonal_top_limit[0]);
             int distance_from_endcell = bottom_rightdiagonal_top_limit[0] - row_value;
             int column_value = Randomfunc(row_value + 1, distance_from_endcell - 1);
             if ("B".equals(matrix[row_value][column_value])) {
                 i--;
 
             }
             matrix[row_value][column_value] = "B";
 //            System.out.println("On Cell " + row_value + "," + column_value + " " + matrix[row_value][column_value]);
             counter += 1;
         }
 //        System.out.println(counter);
     }
 
     public void SouthPlanting(int numBomb) {
         int counter = 0;
         for (int i = 0; i < numBomb; i++) {
             int row_value = Randomfunc(bottom_rightdiagonal_low_limit[0], bottom_rightdiagonal_top_limit[0]);
             int distance_from_endcell = (bottom_rightdiagonal_top_limit[0] - row_value) + 1;
             int column_value = Randomfunc(distance_from_endcell, row_value - 1);
             if ("B".equals(matrix[row_value][column_value])) {
                 i--;
             }
             matrix[row_value][column_value] = "B";
 //            System.out.println("On Cell " + row_value + "," + column_value + " " + matrix[row_value][column_value]);
             counter += 1;
         }
 //        System.out.println(counter);
     }
 
     public void EastPlanting(int numBomb) {
         int counter = 0;
         for (int i = 0; i < numBomb; i++) {
             int column_value = Randomfunc(top_leftdiagonal_low_limit[1], top_leftdiagonal_top_limit[1]);
             int distance_from_endcell = (bottom_rightdiagonal_top_limit[1] - column_value) - 1;
             int row_value = Randomfunc(column_value + 1, distance_from_endcell);
             if ("B".equals(matrix[row_value][column_value])) {
                 i--;
             }
             matrix[row_value][column_value] = "B";
 //            System.out.println("On Cell " + row_value + "," + column_value + " " + matrix[row_value][column_value]);
             counter += 1;
         }
 //        System.out.println(counter);
 
     }
 
     public void WestPlanting(int numBomb) {
         int counter = 0;
         for (int i = 0; i < numBomb; i++) {
             int column_value = Randomfunc(bottom_rightdiagonal_low_limit[1], bottom_rightdiagonal_top_limit[1]);
             int distance_from_endcell = (bottom_rightdiagonal_top_limit[1] - column_value) + 1;
             int row_value = Randomfunc(distance_from_endcell, column_value - 1);
             if ("B".equals(matrix[row_value][column_value])) {
                 i--;
             }
             matrix[row_value][column_value] = "B";
 //            System.out.println("On Cell " + row_value + "," + column_value + " " + matrix[row_value][column_value]);
             counter += 1;
         }
 //        System.out.println(counter);
     }
 //spy's move
     public void spy_algo(String Direction) {
         Thread threadNorth = null;
         Thread threadSouth = null;
         Thread threadEast = null;
         Thread threadWest = null;
 
         // Start all threads in parallel
         if ("North".equals(Direction)) {
             threadNorth = new Thread(new north_spy());
             threadNorth.start();
         }
         if ("South".equals(Direction)) {
             threadSouth = new Thread(new south_spy());
             threadSouth.start();
         }
         if ("East".equals(Direction)) {
             threadEast = new Thread(new east_spy());
             threadEast.start();
         }
         if ("West".equals(Direction)) {
             threadWest = new Thread(new west_spy());
             threadWest.start();
         }
     }
 
     class north_spy implements Runnable{
         @Override
         public void run(){
             //North King Spy Starting Position
             int distance_from_endcell = bottom_rightdiagonal_top_limit[0] - (top_leftdiagonal_top_limit[0] + 1);
             int column_value = Randomfunc(top_leftdiagonal_top_limit[0] + 2, distance_from_endcell - 1);
             matrix[top_leftdiagonal_top_limit[0] + 1][column_value] = "SPY";
             //            System.out.println("On Cell "+(top_leftdiagonal_top_limit[0]+1)+","+column_value+" = "+matrix[top_leftdiagonal_top_limit[0]+1][column_value]);
 
             int row = top_leftdiagonal_top_limit[0] + 1;
             int column = column_value;
             movesSpy1 =Spy_moveN("North", matrix, row, column);
         }
     }
 
     class south_spy implements Runnable{
         @Override
         public void run(){
             //South King Spy Starting Position
             int distance_from_endcell = bottom_rightdiagonal_top_limit[0] - (bottom_rightdiagonal_low_limit[0] - 1);
             int column_value = Randomfunc(distance_from_endcell + 1, bottom_rightdiagonal_low_limit[0] - 2);
             matrix[bottom_rightdiagonal_low_limit[0] - 1][column_value] = "SPY";
 
             //     System.out.println("On Cell "+(bottom_rightdiagonal_low_limit[0]-1)+","+column_value+" = "+matrix[bottom_rightdiagonal_low_limit[0]-1][column_value]);
 
             //Moving the Spy Towards it's kings palace
             int row = bottom_rightdiagonal_low_limit[0] - 1;
             int column = column_value;
             movesSpy2=Spy_MoveS("South",matrix,row,column,bottom_rightdiagonal_top_limit[0]);
 
 
         }
     }
 
     class east_spy implements Runnable{
         @Override
         public void run(){
 //                East King Spy Starting Position
             int distance_from_endcell = bottom_rightdiagonal_top_limit[0] - (top_leftdiagonal_top_limit[0] + 1);
             int row_value = Randomfunc((top_leftdiagonal_top_limit[0] + 2), distance_from_endcell - 1);
             matrix[row_value][top_leftdiagonal_top_limit[0] + 1] = "SPY";
             ////            System.out.println("On Cell "+row_value+","+(top_leftdiagonal_top_limit[0]+1)+" = "+matrix[row_value][top_leftdiagonal_top_limit[0]+1]);
 
             //Moving the Spy Towards it's kings palace
             int row = row_value;
             int column = top_leftdiagonal_top_limit[0]+ 1;
             movesSpy3=Spy_MoveE("East",matrix,row,column);
 
         }
     }
 
     class west_spy implements Runnable{
         @Override
         public void run(){
             //West King Spy Starting Position
             int distance_from_endcell = bottom_rightdiagonal_top_limit[0] - (bottom_rightdiagonal_low_limit[0] - 1);
             int row_value = Randomfunc((distance_from_endcell - 1), bottom_rightdiagonal_low_limit[0] - 2);
             matrix[row_value][bottom_rightdiagonal_low_limit[0] - 1] = "SPY";
             ////            System.out.println("On Cell "+row_value+","+(bottom_rightdiagonal_low_limit[0]-1)+" = "+matrix[row_value][bottom_rightdiagonal_low_limit[0]-1]);
 
             //Moving the Spy Towards it's kings palace
             int row = row_value;
             int column = bottom_rightdiagonal_low_limit[0] - 1;
 
             movesSpy4=Spy_MoveW("West",matrix,row,column,bottom_rightdiagonal_top_limit[0]);
 
 
         }
     }
 //path of spy, refreshing bomb plants
     public void Spy_reached_postplanting(){
         synchronized(this){
             System.out.println("Unique Moves made by SPY of North: " + Arrays.toString(movesSpy1));
             System.out.println("Unique Moves made by SPY of South: " + Arrays.toString(movesSpy2));
             System.out.println("Unique Moves made by SPY of East: " + Arrays.toString(movesSpy3));
             System.out.println("Unique Moves made by SPY of West: " + Arrays.toString(movesSpy4));
             int plantNorth=Randomfunc(100,300);
             int plantEast=Randomfunc(100,300);
             int plantSouth=Randomfunc(100,300);
             int plantWest=Randomfunc(100,300);
             NorthPlanting(plantNorth);
             SouthPlanting(plantSouth);
             EastPlanting(plantEast);
             WestPlanting(plantWest);
             System.out.println("Bomb Replanted..");
         }
     }
 
 
     public static void clearScreen() {
         try {
             if (System.getProperty("os.name").contains("Windows")) {
                 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
             } else {
                 new ProcessBuilder("clear").inheritIO().start().waitFor();
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
 
 
 
     public void KingsMoves(String Direction) {
         Thread threadNorth_King = null;
         Thread threadSouth_King = null;
         Thread threadEast_King = null;
         Thread threadWest_King = null;
 
         // Start all threads in parallel
         if ("North".equals(Direction)) {
             threadNorth_King = new Thread(new kingnorth());
             threadNorth_King.start();
 //            try {
 //                threadNorth_King.join();
 //            } catch (InterruptedException ex) {
 //                ex.printStackTrace();
 //            }
         }
         if ("South".equals(Direction)) {
             threadSouth_King = new Thread(new kingsouth());
             threadSouth_King.start();
 //            try {
 //                threadSouth_King.join();
 //            } catch (InterruptedException ex) {
 //                ex.printStackTrace();
 //            }
         }
         if ("East".equals(Direction)) {
             threadEast_King = new Thread(new kingeast());
             threadEast_King.start();
 //            try {
 //                threadEast_King.join();
 //            } catch (InterruptedException ex) {
 //                ex.printStackTrace();
 //            }
         }
         if ("West".equals(Direction)) {
             threadWest_King = new Thread(new kingwest());
             threadWest_King.start();
 //            try {
 //                threadWest_King.join();
 //            } catch (InterruptedException ex) {
 //                ex.printStackTrace();
 //            }
         }
 
     }
 //converting string array to int array
     public static int[][] convertTo2DIntArray(String[] input) {
         int[][] result = new int[input.length][];
         for (int i = 0; i < input.length; i++) {
             String row = input[i].replace("[", "").replace("]", "");
             String[] stringNumbers = row.split(",\\s*");
             result[i] = Arrays.stream(stringNumbers)
                     .mapToInt(Integer::parseInt)
                     .toArray();
         }
         return result;
     }
 
     private boolean n_reach = false;
     private boolean s_reach = false;
     private boolean e_reach = false;
     private boolean w_reach = false;
     int Allies_N= 150;
     int Allies_S = 150;
     int Allies_E = 150;
     int Allies_W= 150;
  //algo for king's movement
     @Override
     public void King_algo(String[] movespy1,String[] movespy2,String[] movespy3,String[] movespy4,String king){
         int[][] int_moves1 = convertTo2DIntArray(movespy1);
         int[][] int_moves2 = convertTo2DIntArray(movespy2);
         int[][] int_moves3 = convertTo2DIntArray(movespy3);
         int[][] int_moves4 = convertTo2DIntArray(movespy4);
 
         if(king=="North"){
             int row = 0;
             int column = 0;
 
             int Bomb_Deployed_N= 0;
             String end_position = movespy1[0];
             int length_movespy1 = int_moves1.length-1;
             boolean Circuit_triggred=false;
             System.out.println("\t\t.");
             System.out.println("\t\t.");
             System.out.println("North King has Started Moving Towards KING A palace To capture it On the Trail of his SPY not Knowing that Bombs have Repositioned...");
             System.out.println("\t\t.");
             System.out.println("\t\t.");
             System.out.println("This Will potentially Damage King North's team");
             System.out.println("\t\t.");
             System.out.println("\t\t.");
             for(int i=length_movespy1;i>=0;i--){
                 row = int_moves1[i][0];
                 column = int_moves1[i][1];
                 //Bomb Circuit
                 if(matrix[row][column]=="B"){
                     if(row>0 && matrix[row-1][column+1]=="B" && matrix[row-1][column-1]=="B" && matrix[row+1][column-1]=="B" && matrix[row+1][column+1]=="B"){
                         Circuit_triggred=true;
                         System.out.println("North Kingdom Perished..Due to stepping on the connected bombs");
                         this.n_reach = true;
                         Allies_N=0;
                         break;
                     }
                     else if(matrix[row][column-1]=="B" && matrix[row][column+1]=="B" && matrix[row+1][column]=="B"){
                         Circuit_triggred=true;
                         System.out.println("North Kingdom Perished..Due to stepping on 3 consecutive bombs");
                         this.n_reach = true;
                         Allies_N=0;
                         break;
                     }
 //                    else{
 //                        System.out.println("Not Triggered");
 //                    }
                     int death_number = Randomfunc(1,3);
                     Allies_N-=death_number;
                     Bomb_Deployed_N+=1;
                     matrix[row][column]="KN";
 //                    System.out.println("King Moved to"+"["+row+","+column+"]");
 
                 }
                 else if(matrix[row][column]=="x") {
                     matrix[row][column] = "KN";
 //                    System.out.println("King Moved to"+"["+row+","+column+"]");
                 }
             }
             synchronized (this) {
                 if (Circuit_triggred == false) {
                     System.out.println("North King Reached:\n\tAllies Remaining: "+Allies_N+"\n\tBomb Deployed: "+Bomb_Deployed_N+"\n\tTotal Allies Killed: "+(150-Allies_N));
                     this.n_reach = true;
 //                    System.out.println("\tAllies Remaining: " + Allies_N);
 //                    System.out.println("\tBomb Deployed: " + Bomb_Deployed_N);
 //                    System.out.println("\tTotal Allies Killed: " + (150 - Allies_N));
                 }
             }
         }
         else if(king=="South"){
             int row = 0;
             int column = 0;
 
             int Bomb_Deployed_S = 0;
             String end_position = movespy2[0];
             int length_movespy2 = int_moves2.length-1;
             boolean Circuit_triggred= false;
             System.out.println("\t\t.");
             System.out.println("\t\t.");
             System.out.println("South King has Started Moving Towards KING A palace To capture it On the Trail of his SPY not Knowing that Bombs have Repositioned...");
             System.out.println("\t\t.");
             System.out.println("\t\t.");
             System.out.println("This Will potentially Damage King South's team");
             System.out.println("\t\t.");
             System.out.println("\t\t.");
             for(int i=length_movespy2;i>=0;i--){
                 row = int_moves2[i][0];
                 column = int_moves2[i][1];
                 //Bomb Circuit
                 if(matrix[row][column]=="B"){
                     if( row <  bottom_rightdiagonal_top_limit[0] && matrix[row-1][column+1]=="B" && matrix[row-1][column-1]=="B" && matrix[row+1][column-1]=="B" && matrix[row+1][column+1]=="B"){
                         Circuit_triggred=true;
                         System.out.println("South Kingdom Perished..Due to stepping on the connected bombs");
                         this.s_reach = true;
                         Allies_S=0;
                         break;
                     }
                     else if(matrix[row-1][column]=="B" && matrix[row][column+1]=="B" && matrix[row][column-1]=="B"){
                         Circuit_triggred=true;
                         System.out.println("South Kingdom Perished..Due to stepping on 3 consecutive bombs");
                         this.s_reach = true;
                         Allies_S=0;
                         break;
                     }
 //                    else{
 //                        System.out.println("Not triggered");
 //                    }
                     int death_number = Randomfunc(1,3);
                     Allies_S-=death_number;
                     Bomb_Deployed_S+=1;
                     matrix[row][column]="KN";
 //                    System.out.println("King Moved to"+"["+row+","+column+"]");
                 }
                 else if(matrix[row][column]=="x") {
                     matrix[row][column] = "KN";
 //                    System.out.println("King Moved to"+"["+row+","+column+"]");
                 }
             }
             synchronized (this) {
                 if (Circuit_triggred == false) {
                     System.out.println("South King Reached:\n\tAllies Remaining: "+Allies_S+"\n\tBomb Deployed: "+Bomb_Deployed_S+"\n\tTotal Allies Killed: "+(150-Allies_S));
                     this.s_reach = true;
 //                    System.out.println("\tAllies Remaining: " + Allies_S);
 //                    System.out.println("\tBomb Deployed: " + Bomb_Deployed_S);
 //                    System.out.println("\tTotal Allies Killed: " + (150 - Allies_S));
                 }
             }
 
         }
         else if(king=="East"){
             int row = 0;
             int column = 0;
 
             int Bomb_Deployed_E = 0;
             String end_position = movespy3[0];
             int length_movespy3 = int_moves3.length-1;
             boolean Circuit_triggred = false;
             System.out.println("\t\t.");
             System.out.println("\t\t.");
             System.out.println("East King has Started Moving Towards KING A palace To capture it On the Trail of his SPY not Knowing that Bombs have Repositioned...");
             System.out.println("\t\t.");
             System.out.println("\t\t.");
             System.out.println("This Will potentially Damage King East's team");
             System.out.println("\t\t.");
             System.out.println("\t\t.");
             for(int i=length_movespy3;i>=0;i--){
                 row = int_moves3[i][0];
                 column = int_moves3[i][1];
                 //Bomb Circuit
                 if(matrix[row][column]=="B"){
                     if( column > 0 && matrix[row-1][column+1]=="B" && matrix[row-1][column-1]=="B" && matrix[row+1][column-1]=="B" && matrix[row+1][column+1]=="B"){
                         Circuit_triggred=true;
                         System.out.println("East Kingdom Perished..Due to stepping on the connected bombs");
                         this.e_reach = true;
                         Allies_E=0;
                         break;
                     }
                     else if(matrix[row-1][column+1]=="B" && matrix[row+1][column+1]=="B" && matrix[row][column+1]=="B"){
                         Circuit_triggred=true;
                         System.out.println("East Kingdom Perished..Due to stepping on 3 consecutive bombs");
                         this.e_reach = true;
                         Allies_E=0;
                         break;
                     }
 //                    else{
 //                        System.out.println("Not Triggered");
 //                    }
                     int death_number = Randomfunc(1,3);
                     Allies_E-=death_number;
                     Bomb_Deployed_E +=1;
                     matrix[row][column]="KN";
 //                    System.out.println("King Moved to"+"["+row+","+column+"]");
                 }
                 else if(matrix[row][column]=="x") {
                     matrix[row][column] = "KN";
 //                    System.out.println("King Moved to"+"["+row+","+column+"]");
                 }
 
             }
             synchronized (this) {
                 if (Circuit_triggred == false) {
                     System.out.println("East King Reached:\n\tAllies Remaining: "+Allies_E+"\n\tBomb Deployed: "+Bomb_Deployed_E+"\n\tTotal Allies Killed: "+(150-Allies_E));
                     this.e_reach = true;
 //                    System.out.println("\tAllies Remaining: " + Allies_E);
 //                    System.out.println("\tBomb Deployed: " + Bomb_Deployed_E);
 //                    System.out.println("\tTotal Allies Killed: " + (150 - Allies_E));
                 }
             }
 
         }
         else if(king=="West"){
             int row = 0;
             int column = 0;
 
             int Bomb_Deployed_W= 0;
             String end_position = movespy4[0];
             int length_movespy4 = int_moves4.length-1;
             boolean Circuit_triggred = false;
             System.out.println("\t\t.");
             System.out.println("\t\t.");
             System.out.println("West King has Started Moving Towards KING A palace To capture it On the Trail of his SPY not Knowing that Bombs have Repositioned...");
             System.out.println("\t\t.");
             System.out.println("\t\t.");
             System.out.println("This Will potentially Damage King West's team");
             System.out.println("\t\t.");
             System.out.println("\t\t.");
             for(int i=length_movespy4;i>=0;i--){
                 row = int_moves4[i][0];
                 column = int_moves4[i][1];
                 //Bomb Circuit
                 if(matrix[row][column]=="B"){
                     if( column< bottom_rightdiagonal_top_limit[0] && matrix[row-1][column+1]=="B" && matrix[row-1][column-1]=="B" && matrix[row+1][column-1]=="B" && matrix[row+1][column+1]=="B"){
                         Circuit_triggred=true;
                         System.out.println("West Kingdom Perished..Due to stepping on the connected bombs");
                         this.w_reach = true;
                         Allies_W=0;
                         break;
                     }
                     else if(matrix[row-1][column-1]=="B" && matrix[row+1][column-1]=="B" && matrix[row][column-1]=="B"){
                         Circuit_triggred=true;
                         System.out.println("West Kingdom Perished..Due to stepping on 3 consecutive bombs");
                         this.w_reach = true;
                         Allies_W=0;
                         break;
                     }
 //                    else{
 //                        System.out.println("not triggered");
 //                    }
                     int death_number = Randomfunc(1,3);
                     Allies_W-=death_number;
                     Bomb_Deployed_W+=1;
                     matrix[row][column]="KN";
 //                    System.out.println("King Moved to"+"["+row+","+column+"]");
                 }
                 else if(matrix[row][column]=="x"){
                     matrix[row][column]="KN";
 //                    System.out.println("King Moved to"+"["+row+","+column+"]");
                 }
             }
             synchronized (this) {
                 if (Circuit_triggred == false) {
                     System.out.println("West King Reached:\n\tAllies Remaining: "+Allies_W+"\n\tBomb Deployed: "+Bomb_Deployed_W+"\n\tTotal Allies Killed: "+(150-Allies_W));
                     this.w_reach = true;
 //                    System.out.println("\tAllies Remaining: " + Allies_W);
 //                    System.out.println("\tBomb Deployed: " + Bomb_Deployed_W);
 //                    System.out.println("\tTotal Allies Killed: " + (150 - Allies_W));
                 }
             }
         }
     }
 
 //final result
 public void Result(){
     if (this.n_reach && this.s_reach && this.e_reach && this.w_reach) {
         int f1;
         int f2;
         String f1_S;
         String f2_S;
         if(Allies_N==0 && Allies_S==0 && Allies_E==0 && Allies_W==0 ){
             System.out.println("King A Successfully Retained his Castle...");
         }
         else {
             if (Allies_N >= Allies_S) {
                 f1 = Allies_N;
                 f1_S = "North";
             } else {
                 f1 = Allies_S;
                 f1_S = "South";
             }
             if (Allies_E >= Allies_W) {
                 f2 = Allies_E;
                 f2_S = "East";
             } else {
                 f2 = Allies_W;
                 f2_S = "West";
             }
 
             if (f1 >= f2) {
                 System.out.println(f1_S + " King Was Successful in Capturing the Palace Due to More Number of Allies.." + f1);
             } else{
                 System.out.println(f2_S + " King Was Successful in Capturing the Palace Due to More Number of Allies.." + f2);
             }
 
         }
     }
 }
 
 
 
 
 
 
     class kingwest implements Runnable{
         @Override
         public void run(){
             King_algo(movesSpy1,movesSpy2,movesSpy3,movesSpy4,"West");
         }
     }
 
 
     class kingeast implements Runnable{
         @Override
         public void run(){
             King_algo(movesSpy1,movesSpy2,movesSpy3,movesSpy4,"East");
         }
     }
 
 
     class kingsouth implements Runnable{
         @Override
         public void run(){
             King_algo(movesSpy1,movesSpy2,movesSpy3,movesSpy4,"South");
 
         }
     }
 
     class kingnorth implements Runnable{
         @Override
         public void run(){
             King_algo(movesSpy1,movesSpy2,movesSpy3,movesSpy4,"North");
         }
     }
 
 
 
 
 
     public static void main(String args[]) {
         Foundation f1 = new Foundation();
         f1.Diagonal();
         f1.planting();
         f1.spy_algo("North");
         f1.spy_algo("South");
         f1.spy_algo("East");
         f1.spy_algo("West");
         try {
             Thread.sleep(20000);
             System.out.println("Replanting Bomb...");
         } catch (InterruptedException ex) {
             ex.printStackTrace();
         }
         System.out.println("Start Position of Spy of North: "+f1.movesSpy1[0]);
         System.out.println("Start Position of Spy of South: "+f1.movesSpy2[0]);
         System.out.println("Start Position of Spy of East: "+f1.movesSpy3[0]);
         System.out.println("Start Position of Spy of West: "+f1.movesSpy4[0]);
 //        f1.Display();
         System.out.println("Final Position of Spy of North: "+f1.movesSpy1[f1.movesSpy1.length-1]);
         System.out.println("Final Position of Spy of South: "+f1.movesSpy2[f1.movesSpy2.length-1]);
         System.out.println("Final Position of Spy of East: "+f1.movesSpy3[f1.movesSpy3.length-1]);
         System.out.println("Final Position of Spy of West: "+f1.movesSpy4[f1.movesSpy4.length-1]);
         f1.Display("Clear");
         f1.Spy_reached_postplanting();
 //        f1.Display();
         try {
             Thread.sleep(2000);
         } catch (InterruptedException ex) {
             ex.printStackTrace();
         }
         f1.KingsMoves("North");
         f1.KingsMoves("South");
         f1.KingsMoves("East");
         f1.KingsMoves("West");
         try {
             Thread.sleep(500);
         } catch (InterruptedException ex) {
             ex.printStackTrace();
         }
 //        f1.results();
 //        f1.Display();
 //        System.out.println(f1.Results[1]);
 //        System.out.println("triggered" + top_leftdiagonal_top_limit[0] + "," + top_leftdiagonal_top_limit[1]);
 //        System.out.println("triggered" + top_leftdiagonal_low_limit[0] + "," + top_leftdiagonal_low_limit[1]);
 //        System.out.println("triggered" + bottom_rightdiagonal_low_limit[0] + "," + bottom_rightdiagonal_low_limit[1]);
 //        System.out.println("triggered" + bottom_rightdiagonal_top_limit[0] + "," + bottom_rightdiagonal_top_limit[1]);
             f1.Result();
 //            f1.Display();
 
 //
     }
 }
 