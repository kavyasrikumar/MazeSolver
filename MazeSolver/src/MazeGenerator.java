/**
 * Name: Kavya Srikumar
 * Last Updated: 2/1/22
 * Period: 3
 * 
 * Creates new mazes. Please refer to the spec for instructions on how to generate mazes.
 * 
 * This algorithm takes a parameter for size, and creates a maze accordingly. 
 * There is only one solution path between any given start and end point, which 
 * are both generated randomly. There are no inaccessible areas. 
 * 
 * The algorithm functions by driving through the maze and removing walls until
 * a cell with no unvisited neighbors is reached, and words backwards until all 
 * cells are visited. 
 * 
 */

import java.util.ArrayList;
import java.lang.Math;

public class MazeGenerator
{
	
	private Maze myMaze;
	
	// Populates with an unvisited neighbor and its direction
	//
	private Cell neighbor;
	private Direction direction; // valid only when neighbor is not null
	
    /**
     * Randomly generates a perfect maze of {@param size}.
     *
     * @param size the size of the maze to generate
     * @return the generated maze
     */
    public Maze generate(int size)
    { 	
    	
    	myMaze = new Maze(size);
    	
    	Stack<Cell> myStack = new Stack<Cell>();
    	myStack.push (new Cell(0,0));    	
    	
    	while (myStack.isEmpty() == false) 
    	{
    		
    		// pop the current cell until you reach the last cell which has no neighbors
    		Cell current = myStack.pop();
    		
    		// mark it visited
    		myMaze.visit(current.getX(), current.getY());
    		
    		// get the next unvisited neighbor
    		getRandomUnvisitedNeighbor(current.getX(), current.getY());
    		
    		// if a neighbor exists
    		if (neighbor != null) 
    		{
    			myMaze.removeWall (current.getX(), current.getY(), direction);
    			
    			myStack.push(current);
    			myStack.push(neighbor);
    		}
    	}
        
    	myMaze.setStart ((int)(Math.random() * size), (int)(Math.random() * size));
    	myMaze.setEnd ((int)(Math.random() * size), (int)(Math.random() * size));
    	
    	return myMaze;
    }
    
    public void getRandomUnvisitedNeighbor(int x, int y)
    {
    	ArrayList<Cell> myArray = new ArrayList<Cell>();
    	ArrayList<Direction> myDirection = new ArrayList<Direction>();
    	
    	if ( (x - 1 >= 0) && !myMaze.isVisited(x - 1, y) ) 
    	{
    		myArray.add (new Cell(x -1, y));
    		myDirection.add (Direction.LEFT);
    	}
    	
    	if ( (y - 1 >= 0)  && !myMaze.isVisited(x, y - 1)) 
    	{
    		myArray.add (new Cell(x, y - 1));
    		myDirection.add (Direction.DOWN);
    	}
    	
    	if ( (x + 1 < myMaze.size()) && !myMaze.isVisited(x + 1, y) ) 
    	{
    		myArray.add (new Cell(x + 1, y));
    		myDirection.add (Direction.RIGHT);
    	}
    	
    	if ( (y + 1 < myMaze.size()) && !myMaze.isVisited(x, y + 1) ) 
    	{
    		myArray.add (new Cell(x, y + 1));
    		myDirection.add (Direction.UP);
    	}
    	
    	// neighbor will be null unless the current cell has a neighbor
    	neighbor = null;
    	
    	if (myArray.size() > 0) 
    	{
    		// use a random number within the range 0 <= x < arraySize 
    		// to choose a neighbor randomly
    		//
    		double rand = (Math.random() * (myArray.size() - 0.01));
    		
        	int index = (int)(rand);
        	
        	neighbor = myArray.get(index);
        	direction = myDirection.get(index);
    	}
    }
    
    /**
     * Creates and draws a sample maze. Try generating mazes with different sizes!
     *
     * @param args unused
     */
    public static void main(String[] args)
    {
        StdRandom.setSeed(34);
        int size = 128; // Setting above 200 is not recommended!
        MazeGenerator generator = new MazeGenerator();
        Maze maze = generator.generate(size);
        maze.draw();
    }
}
