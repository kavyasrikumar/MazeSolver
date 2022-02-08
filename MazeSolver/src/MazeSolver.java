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
    	
    	AddNeighbor (maze.getStart(), null);
    	
    	Queue<Cell> myQueue = new Queue<Cell>();
    	myQueue.enqueue(maze.getStart());
    	
    	while (myQueue.isEmpty() == false) 
    	{
    		// pop the current cell and mark it as visited
    		Cell current = myQueue.dequeue();
    		maze.visit(current.getX(), current.getY());
    		
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

    public Path getPath ()
    {
    	Path myPath = new Path();

    	// Construct the path from the start to the end and return it.   
		//
		myPath.addFirst(myMaze.getEnd());

		int cx = myMaze.getEnd().getX();
		int cy = myMaze.getEnd().getY();
		
		// Now walk through the stack and populate the path
		//
		while (myPathStack.isEmpty() == false) {
			
			stackElement l = myPathStack.pop();
			if (l.current.getX() == cx && l.current.getY() == cy) {	
				
				// The start has a previous of null. ignore it
				//
				if (l.previous != null) {
					myPath.addFirst (l.previous);
					cx = l.previous.getX();
					cy = l.previous.getY();
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
    // adds all the found neighbors to the queue
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

