/**
 * Name: Kavya Srikumar
 * Last Updated: 2/1/22
 * Period: 3
 * 
 * The algorithm to do this works as follows:
 * 1.Initialize a queue with the start cell.
 * 2.Loop until the queue is empty.
 * 	a.Dequeue the current cell and mark it as visited.
 * 	b.If the current cell is the end cell.
 * 		i.Done! Construct the path from the start to the end and return it.
 * 	c.Enqueue all cells for neighbors of the current cell that are reachable and unvisited.
 * 3.If all reachable cells have been visited and no solution has been found, then return no solution.
 * 
 * Solves mazes. Please refer to the specification for instructions on how to solve mazes.
 */

import java.util.ArrayList;

public class MazeSolver
{
    /**
     * Provides a solution for a given maze, if possible. A solution is a path from the start cell
     * to the finish cell (inclusive). If there is no solution to the maze then returns the static
     * instance {@link Path#NO_PATH}. If the maze is perfect then there must be only one solution.
     *
     * @param maze the maze to solve
     * @return a solution for the maze or {@link Path#NO_PATH} if there is no solution
     */
	
	private Maze myMaze;
	
    public Path solve(Maze maze)
    {
    	myMaze = maze;
    	Path myPath = new Path();
    	
    	Queue<Cell> myQueue = new Queue<Cell>();
    	myQueue.enqueue(maze.getStart());
    	
    	while (myQueue.isEmpty() == false) 
    	{
    		
    		// pop the current cell until you reach the last cell which has no neighbors
    		Cell current = myQueue.dequeue();
    		
    		// mark it visited
    		maze.visit(current.getX(), current.getY());
    		
    		if(current.equals(maze.getEnd()))
    		{
    			//Construct the path from the start to the end and return it.
    			myPath.addFirst(current);
    			
    			while(myQueue.isEmpty() == false) 
    			{
    				myPath.addFirst(myQueue.dequeue());
    			}
    		}
    		
    		if (getReachableUnvisitedNeighbors(current) != null)
    		{
        		// if current is not the end of the path, get neighbors
        		ArrayList<Cell> neighbors = getReachableUnvisitedNeighbors(current);
        		
        		for( int i = 0; i < neighbors.size(); i++) 
        		{
        			myQueue.enqueue(neighbors.get(i));
        		}
    		}

    	}
    	
    	return Path.NO_PATH;
    }

    public ArrayList<Cell> getReachableUnvisitedNeighbors(Cell cell)
    {
    	int x = cell.getX();
    	int y = cell.getY();
    	
    	ArrayList<Cell> myArray = new ArrayList<Cell>();
    	
    	if ( !myMaze.isVisited(x - 1, y) && myMaze.isOpen(x - 1, y, Direction.LEFT) ) 
    	{
    		myArray.add (new Cell(x -1, y));
    	}
    	
    	if ( !myMaze.isVisited(x, y - 1) && myMaze.isOpen(x, y - 1, Direction.DOWN) ) 
    	{
    		myArray.add (new Cell(x, y - 1));
    	}
    	
    	if ( !myMaze.isVisited(x + 1, y) && myMaze.isOpen(x + 1, y, Direction.RIGHT)  ) 
    	{
    		myArray.add (new Cell(x + 1, y));
    	}
    	
    	if ( !myMaze.isVisited(x, y + 1) && myMaze.isOpen(x, y + 1, Direction.UP) ) 
    	{
    		myArray.add (new Cell(x, y + 1));
    	}
    	
    	if(myArray.size() == 0) 
    	{
    		return null;
    	}
    	return myArray;
    }
    
    /**
     * Creates, solves, and draws a sample maze. Try solving mazes with different sizes!
     *
     * @param args unused
     */
    public static void main(String[] args)
    {
        // First, generate a new maze.
        int size = 10; // Setting above 200 is not recommended!
        MazeGenerator generator = new MazeGenerator();
        Maze maze = generator.generate(size);
        maze.freeze();

        // Next, solve it!
        MazeSolver solver = new MazeSolver();
        maze.resetVisited();
        Path solutionPath = solver.solve(maze);
        maze.setSolution(solutionPath);

        // This is so we can see which cells were explored and in what order.
        maze.setDrawVisited(true);

        maze.draw();
    }
}
