/**
 * Name: Kavya Srikumar
 * Last Updated: 1/21/22
 * Period: 3
 * 
 * Creates new mazes. Please refer to the spec for instructions on how to generate mazes.
 * 
 * The algorithm to do this works as follows:
 * 1.Initialize a stack starting at (0, 0). 
 * 2.Loop until the stack is empty.
 * 	a.Pop the current cell off the stack and mark it as visited.
 * 	b.If the current cell has any unvisited neighbors.
 *	 	i.Choose a random unvisited neighbor cell. 
 *	 	ii.Remove the wall between the chosen neighbor cell and the current cell.
 * 		iii.Push the current cell onto the stack.
 * 		iv.Push the randomly chosen neighbor cell onto the stack.
 */
public class MazeGenerator
{
	
	Maze myMaze;
	
	// Populates with an unvisited neighbor and its direction
	//
	Cell neighbor;
	Direction direction; // valid only when neighbor is not null
	
    /**
     * Randomly generates a perfect maze of {@param size}.
     *
     * @param size the size of the maze to generate
     * @return the generated maze
     */
    public Maze generate(int size)
    { 	
    	myMaze = new Maze(size);
    	
    	Stack<Cell> myStack = new Stack<Cell>;
    	myStack.push (new Cell(0,0));
    	
    	while (myStack.isEmpty() == false) {
    		
    		// pop the current cell
    		Cell current = myStack.pop();
    		
    		// mark it visited
    		myMaze.visit(current.getX(), current.getY());
    		
    		// get the next unvisited neighbor
    		getRandomUnvisitedNeighbor(current.getX(), current.getY());
    		
    		// If a neighbor exists
    		if (neighbor != null) {
    			myMaze.removeWall (current.getX(), current.getY(), direction);
    			
    			myStack.push(current);
    			myStack.push(neighbor);
    		}
    	}
        
    	return myMaze;
    }
    
    public void getRandomUnvisitedNeighbor (int x, int y)
    {
    	ArrayList<Cell> myArray = new ArrayList<Cell>;
    	ArrayList<Direction> myDirection = new ArrayList<Direction>;
    	
    	if ((x - 1 >= 0) && !myMaze.isVisited (x-1, y)) {
    		myArray.add (new Cell(x-1, y));
    		myDirection.add (Direction.LEFT);
    	}
    	
    	if ((y - 1 >= 0)  && !myMaze.isVisited (x, y - 1)) {
    		myArray.add (new Cell(x, y -1));
    		myDirection.add (Direction.DOWN);
    	}
    	if ((x+1 < myMaze.size()) &&  && !myMaze.isVisited (x+1, y)) {
    		myArray.add (new Cell(x+1, y));
    		myDirection.add (Direction.RIGHT);
    	}
    	
    	if ((y +1 < myMaze.size()) &&  && !myMaze.isVisited (x, y+1)) {
    		myArray.add (new Cell(x, y + 1));
    		myDirection.add (Direction.UP);
    	}
    	
    	neighbor = null;
    	
    	if (myArray.size() > 0) {
        	int index = (int) (Math.Random() * myArray.size()); 
        	
        	neighbor = myArray[index];
        	direction = myDirection[index];
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
        int size = 10; // Setting above 200 is not recommended!
        MazeGenerator generator = new MazeGenerator();
        Maze maze = generator.generate(size);
        maze.draw();
    }
}
