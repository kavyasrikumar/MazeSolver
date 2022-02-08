/**
 * Name: Kavya Srikumar
 * Last Updated: 2/7/22
 * Period: 3
 * 
 * Finds a path from a specified start cell to a specified end cell. 
 * Starts by enqueueing a cell, marking as visited, and enqueuing all its unvisited neighbors. 
 * Repeats the process until arriving at the end cell, and uses pairs of cells to backtrack 
 * and draw the path. 
 * 
 * Returns no solution if all cells are visited and no solution can be found. 
 * 
 * Solves mazes. Please refer to the specification for instructions on how to solve mazes.
 */

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
	
	// creates a reference to the maze that will be solved
	//
	private Maze myMaze;

	// creates a stackElement which is a set of two cells
	//
	class stackElement 
	{
	  	Cell current;
        Cell previous;  
        
        stackElement (Cell c, Cell p) {
        	current = c;
        	previous = p;
        }
	}
	
	// create a stack of pairs of cells
	// each pair is a cell that is visited and its previous
    //
	Stack<stackElement>  myPathStack;
	
	public void AddNeighbor (Cell current, Cell previous) 
	{	
		stackElement l = new stackElement (current, previous);
    	myPathStack.push(l);		
	}
	

    public Path solve(Maze maze)
    {
    	myMaze = maze;
    	
    	myPathStack = new Stack<stackElement>();
    	
    	// add each visited cell and its neighbor
    	// the neighbor cells previous would be the visited cell
    	//
    	AddNeighbor (maze.getStart(), null);
    	
    	Queue<Cell> myQueue = new Queue<Cell>();
    	myQueue.enqueue(maze.getStart());
    	
    	while (myQueue.isEmpty() == false) 
    	{
    		// pop the current cell and mark it as visited
    		Cell current = myQueue.dequeue();
    		maze.visit(current.getX(), current.getY());
    		
    		// return the path if end has been found
    		//
    		if(current.equals(maze.getEnd()))
    		{
    			return getPath();
    		}
    		
    		if(allVisited(maze, maze.size())) {
    			return Path.NO_PATH;
    		}

    		// if current is not the end of the path, get and queue neighbors
    		getandQueueAllReachableUnvisitedNeighbors(current, myQueue);        		
    	}
    	
    	return Path.NO_PATH;
    }

    // get the path
    public Path getPath ()
    {
    	Path myPath = new Path();

    	// Construct the path from the start to the end and return it.   
		//
		myPath.addFirst(myMaze.getEnd());

		int currentX = myMaze.getEnd().getX();
		int currentY = myMaze.getEnd().getY();
		
		// Now walk through the stack and populate the path
		// by comparing the previous to the current of the following
		// and creating the correct sequence of cells
		//
		while (myPathStack.isEmpty() == false) {
			
			stackElement tempCell = myPathStack.pop();
			if (tempCell.current.getX() == currentX && tempCell.current.getY() == currentY) {	
				
				// The start has a previous of null
				//
				if (tempCell.previous != null) {
					myPath.addFirst (tempCell.previous);
					currentX = tempCell.previous.getX();
					currentY = tempCell.previous.getY();
				}
			}
		}
		return myPath;
    }
    
    
    // checks to see if every Cell in the maze is visited
    //
    public boolean allVisited (Maze maze, int size) 
    {    	
    	for( int i = 0; i < size; i++ )
    	{
    		for( int j = 0; j < size; j++)
    		{
    			if(!maze.isVisited(i, j)) {
    				return false;
    			}
    		}
    	}
    	return true;
    }

    // finds all unvisited neighbors of the cell that is passed in
    // adds all the found neighbors to the queue if there is no wall
    // between the cells
    //
    public void getandQueueAllReachableUnvisitedNeighbors(Cell cell, Queue<Cell> queue)
    { 	
    	int x = cell.getX();
    	int y = cell.getY();
    	
    	if ( (x - 1 >= 0) && !myMaze.isVisited(x - 1, y) && myMaze.isOpen(x, y, Direction.LEFT) ) 
    	{
    		Cell c = new Cell(x - 1, y);
    		
    		AddNeighbor(c, cell);
    		queue.enqueue(c);
    	}
    	
    	if ( (y - 1 >= 0)  && !myMaze.isVisited(x, y - 1) && myMaze.isOpen(x, y, Direction.DOWN) ) 
    	{
    		Cell c = new Cell(x, y - 1);
    		
    		AddNeighbor(c, cell);
    		queue.enqueue(c);
    	}
    	
    	if ( (x + 1 < myMaze.size()) && !myMaze.isVisited(x + 1, y) && myMaze.isOpen(x, y, Direction.RIGHT)  ) 
    	{
    		Cell c = new Cell(x + 1, y);
    		
    		AddNeighbor(c, cell);
    		queue.enqueue(c);		
    	}
    	
    	if ( (y + 1 < myMaze.size()) && !myMaze.isVisited(x, y + 1) && myMaze.isOpen(x, y, Direction.UP) ) 
    	{
    		Cell c = new Cell(x, y + 1);   		
    		AddNeighbor(c, cell);
    		
    		queue.enqueue(c);
       	}
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

