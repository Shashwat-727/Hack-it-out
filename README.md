# Hack-it-out
🌟 Thrilled to Share My Experience at Hack-It-Out! 🌟

I recently had the incredible opportunity to participate in the Hack-It-Out competition organized by Thakur College of Science and Commerce. The event was a fantastic platform to showcase creativity, problem-solving, and teamwork.

🧩 Problem Statement:
Assuming there is a 50 X 50 matrix, in center of the matrix there will be a king's castle we'll Call the king's Castle as King A's Castle, now on the North, South, East, West Direction of the matrix there will be 4 different King's;
We'll Call them NorthKing , SouthKing, EastKing , WestKing, and these King has to move one-cell-by-one-cell towards the King A's Castle to capture it..but the Twist is in their way there will be RANDOMLY arranged Landmines;
So in-order for All the King's of Different Direction to reach Safely to the King A's Castle they need a Safe path..therefore each king has a spy which will Start from the King A's Castle and will Come Back to their Respective King's;
These Spy will note the path on which they didn't find the bomb and they'll hand out the safe path to the King..but the twist is that After the Spies have Reached to their Respective King's (North, South, East , West) the Bomb Planting is Reshuffled;
So Now the (North, South, East, West) King's will follow the path given by their Spies but the bomb's are reshuffled and they don't know about it..and considering Each king has 150 number of soilder's;
condition 1- So if their is a bomb on the path given by spy any random number of Solider will die consider range (1-3);
condition 2- if Soilders Steps on 3 Consicutive Bomb than Whole Army with the King dies;
condition 3- if Bomb is in X pattern, let's say their are land mines on ([0,0], [1,1], [0,2], [2,0], [2,2]) so the center mine is on [1,1] so if the King Steps on the Centre Mine All the Bomb's will explode in the X pattern and the Whole Army with the king will die;
Keeping All this Condition's in the Mind we have to Simulate a program so that it Output's which king Reached the King A's Castle and with how many allies or if the King A Retained it's Castle and Rest of the King Died while Trying to approad King A's Castle;


💡 My Approach and Contribution:


1. _**Foundation.java**_
This class serves as the main driver for the application and implements logic for setting up the game. It combines various algorithms to simulate a battle strategy involving multiple kings and their spies.

Core Concept:

King A defends their palace at the center of a 50x50 grid (matrix).
Four other kings (North, South, East, and West) deploy spies to locate bombs (landmines) around King A's palace.
The spies find a path to avoid detonating bombs and return their findings to their respective kings.
The kings then attempt to capture King A’s palace using the paths identified by their spies.
Key Features:

Matrix Initialization:

A 50x50 grid is initialized with bombs ('B'), non-bomb tiles ('x'), and a palace represented by 'K'.
Diagonals are predefined to simulate boundaries.
Bomb Planting:

Bombs are planted randomly in the grid in the territories of the North, South, East, and West kings.
Spy Movement Logic:

Spies from each king move across the grid in their respective territories.
The moves are calculated using algorithms implemented in SPYAlgo.
King Movement:

After the spies identify bomb-free paths, each king attempts to reach King A’s palace following these paths.
If a king steps on a bomb, there are consequences such as losing allies or triggering a connected bomb circuit.
Result Evaluation:

After all movements, the king with the most remaining allies or successful capture strategy is declared the winner.



2. _**SPYAlgo.java**_
This abstract class contains the core algorithms for the spies’ movement and pathfinding logic.

Spy Movement Algorithms:

Spy_moveN (North Spy):
Moves the spy towards King A's palace from the north, avoiding bombs and marking visited paths.
Spy_MoveS (South Spy), Spy_MoveE (East Spy), Spy_MoveW (West Spy):
Similar to Spy_moveN, but customized for each direction.
Spies avoid bombs (B), and each move is stored in a Set to ensure unique paths.
Bomb Detection and Defusing:

If a spy encounters a bomb, it attempts to "defuse" it by simulating a delay (using Thread.sleep).



3. _**King.java**_
This interface defines the method for kings' movements (King_algo).

King Movement Logic:
Each king uses the paths found by their spies to move towards King A’s palace.
If they encounter bombs, they lose allies or may trigger a bomb circuit that causes severe damage.
How It Works Together:
Initialization:

The Foundation class initializes the grid, plants bombs, and creates paths for spies.
Spy Pathfinding:

Spies from each direction find bomb-free paths using algorithms in SPYAlgo.
Bomb Repositioning:

After spies report back, bombs are randomly repositioned to add unpredictability.
King Movement:

Kings move along the paths identified by their spies and attempt to capture King A’s palace.
Result Evaluation:

The final outcome determines whether King A retains their palace or one of the other kings successfully captures it.

_**A EXCEL SHEET IS PROVIDED TO UNDERSTAND THE LOGIC WELL(LOGIC WAS DEVELOPED USING THAT EXCEL SHEET).**_



