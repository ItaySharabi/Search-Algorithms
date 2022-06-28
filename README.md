# AI-Search-Algorithms
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="java" height="40"/>


# How to Run?

Download this repository's source files by clicking Code -> Download ZIP:

<img src="https://user-images.githubusercontent.com/63110245/171963689-c52f95d4-007a-4da9-95a9-c15bf408bfd9.png" height=300 width=350>

or,
you can use a `git bash` terminal (On windows) to clone this repository by running the command:

`git clone https://github.com/ItaySharabi/AI-Search-Algorithms.git`

<img src="https://user-images.githubusercontent.com/63110245/171963918-20077df2-c190-47df-8a15-c7f638a37193.png" height=90 width=650>

After you have the source files:

### Using any Java workspace (IntelliJ, Eclipse, ...)
Open the `MarblesPuzzle` package and look for `SearchEngine.java` file.

Run it and enjoy!


This file consumes an input file given to it by default, 
but you can always create a new input file and feed it to the `MarblesPuzzle.SearchEngine.java` file.

![image](https://user-images.githubusercontent.com/63110245/170684815-542fb58b-4ee8-4ffd-a2cf-5f59a1899079.png)

## Example input file for execution:
You can write your own input files in the folder `MarblesPuzzle/Inputs`
and simply change the input file in `MarblesPuzzle.SearchEngine.java`

![image](https://user-images.githubusercontent.com/63110245/170685225-03f7bf4e-71c2-406c-a217-f032546fd713.png)

___________________________________________________________________________________________________________________________________

# Algorithms:

<!-- ## BFS -->
<h1 align="center"> BFS </h1>

![M4EM](https://user-images.githubusercontent.com/63110245/170673102-d61df475-ea71-4ab5-ae4b-105725083743.gif)

<h1 align="center"> DFID </h1>

![image](https://user-images.githubusercontent.com/63110245/170671480-4e06acda-3657-404b-b051-e4e213369dea.png)

# Informed Search algorithms

<h1 align="center"> A* </h1>
A* (A Star) is a very special algorithm.
It is an "informed algorithm", which evaluates nodes at the time of search,
with a special estimation method which is being applied specifically to `State`s and not `Node`s - `Herusitic Evaluation`.

A* is special because it relies only on a given heuristic evaluation (See `Manhattan Distance`) function,
`h(x) : V --> [0, N]`, which if accurate enough - produces amazing path-finding solutions!

If visualizing A* - then you can see how it avoids going in wrong directions, and you can see it 
bursting towards the target!

![image](https://user-images.githubusercontent.com/63110245/170674478-1adca660-02c3-4b4a-b303-a3e27b810b2b.png)



<!-- ## IDA*


## DFBnB -->
