/**
 * Name: Kavya Srikumar
 * Last Updated: 1/21/22
 * Period: 3
 * 
 * Creates new mazes. Please refer to the spec for instructions on how to generate mazes.
 */
public class MazeGenerator
{
	
	private int maxSize;
    /**
     * Randomly generates a perfect maze of {@param size}.
     *
     * @param size the size of the maze to generate
     * @return the generated maze
     */
    public Maze generate(int size)
    {
    	maxSize = size; 
    	
    	Stack<Cell> current = new Stack<Cell>;
        throw new UnsupportedOperationException("Implement me!");
    }

    public int markVisited(int x, int y)
    {
    	// set visitedArray [x][y] = 1
    	return x;
    }
    
    public boolean isVisited (int x, int y)
    {
    	if(markVisited(x, y) == 1)
    	{
    		return true;
    	}
    	
    	return false;
    }
    
    public Cell[] getNeighbor (int x, int y)
    {
    	Cell[] neighbors = new Cell[4];
    	int index = 0;
    	
    	if ((markVisited(x+1, y) != 1) && x+1 < maxSize && y < maxSize )
    	{
    		neighbors[index++] = (x, y);
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
