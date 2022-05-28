# Marbles Puzzle Game Solving Algorithms
This project is all about finding (The best?) solutions for
a certain problem, for instance: `Arrange the Marbles` Game,
which is a very simple game, but when it comes to figuring out
in what search space we're dealing with, finding a solution could raise some questions....
Questions like:
What is the best solution for me?

Is the best solution the one with the smallest possible amount of moves?

Is it the one with no matter how many moves - but lowest cost? (Cost == effort/payments/time/...)

This project demonstrates 5 different search algorithms, for finding a solution to given
situations of the game.

Just write an input file (`input1.txt` is an example file. More
are to be found in `Inputs` folder) and plug it into the constructor
of IOHandler.
## Rules:
Moving marbles of any color is possible in 4 directions: UP, DOWN, LEFT, RIGHT,
Unless the marble is blocked in that direction.
The `costs` of moving marbles:

Red - 1

Blue - 2

Green - 10

Yellow - 1 (Only in 5x5 Game Boards)

Try getting from this initial board state:

<img src="https://user-images.githubusercontent.com/63110245/170682891-17fc3b36-16ae-4e4c-88b3-352cbf9d0931.png" width="200" height="175">

To this goal board state:

<img src="https://user-images.githubusercontent.com/63110245/170682978-3db1a590-03f7-4399-9224-7abf0b030153.png" width="200" height="175">

## In Two Ways!
1) Find the shortest solution
2) Find the lowest cost solution

Hint: In this example they may be the same.

# How to Run?

Simply download this repository's source files,
or,
you can use a `git bash` terminal (On windows) to clone this repository using the command:
`git clone https://github.com/ItaySharabi/AI-Search-Algorithms.git`

After you have the source files - just open the `MarblesPuzzle` package
and run `SearchEngine.java`

This file processes the input file given to it by default, 
but you can always create a new input file and feed it to the `SearchEngine.java` file.
![image](https://user-images.githubusercontent.com/63110245/170684815-542fb58b-4ee8-4ffd-a2cf-5f59a1899079.png)

## Example input file for execution:
You can write your own input files in the folder `MarblesPuzzle/Inputs`
and simply change the input file in `SearchEngine.java`

![image](https://user-images.githubusercontent.com/63110245/170685225-03f7bf4e-71c2-406c-a217-f032546fd713.png)



# Heuristic Evaluation
In large `Search Spaces` we can get lost looking for targets which are far off the starting point
and for that we could use some sort of "Navigation" tips for the algorithms when they search.
This is where Heuristic Evaluation comes in.
We define a heuristic evaluation function `h(x) : V --> [0, N]`, which operates
on `State`s and assigns a "score" (Heuristic evaluation) for each one.
The best score should be 0.
And the only State who should be graded 0 is the target state!

Mathematically, if `s` is the initial state and `g` is the goal state,
then: `h(s) > 0` and `h(g) = 0`

## Manhattan Distance
For this problem search, I've picked the `Manhattan Distance` evaluation function.
Because there are multiple marbles of the same color on each board - 
I've had to tweak the naive implementation of the heuristic evaluation function.
My improvement was using a boolean matrix of handled marbles to avoid duplicates
and a `punishment` of +1 score (Further away from target) to marble that should be moved and 
is blocked from all directions!

Definition:
The distance between two points measured along axes at right angles.
In a plane with `p1` at `(x1, y1)` and `p2` at `(x2, y2)`, it is `|x1 - x2| + |y1 - y2|`.


<img src="https://d2mk45aasx86xg.cloudfront.net/image5_11zon_7723f44a19.webp" width="300" height="250">
