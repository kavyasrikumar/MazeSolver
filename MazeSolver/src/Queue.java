/**
 * Name: Kavya Srikumar
 * Last Updated: 1/21/22
 * Period: 3
 * 
 * A first-in-first-out (FIFO) queue of generic items.
 *
 * @param <T> the type of item to store in the queue
 */
public class Queue<T>
{
	private T[] array;
    private int size;
    private int maximumSize;
    /**
     * Initializes an empty queue.
     */
    public Queue()
    {
        array = (T[])new Object[1];
        maximumSize = 1;
        size = 0;
    }

    /**
     * Adds an item to the queue.
     *
     * @param newItem the item to add
     */
    public void enqueue(T newItem)
    {
        T[] newArray;
        if (newItem == null)
        {
            throw new IllegalArgumentException();
        }
        if(size >= maximumSize ) 
        { 
            maximumSize *= 2;
            newArray = (T[]) new Object[maximumSize];
            for ( int i = 0; i < size; i++ )
            {
                newArray[i] = array[i];
            }
            this.array = newArray;
                
        } 
        array[size] = newItem;
        size++;
    }

    /**
     * Removes and returns the item on the queue that was least recently added.
     *
     * @return the item on the queue that was least recently added
     */
    public T dequeue()
    {
        T temp;
        if (size == 0 )
        {
            throw new IllegalStateException(); 
        } 
        if (maximumSize / 4 >= size){
            
            maximumSize /= 2;
            
            T[] newArray = (T[]) new Object[maximumSize];
            
            for ( int i = 0; i < maximumSize; i++ )
            {
                newArray[i] = array[i];
            }
            
            this.array = newArray;
        
        }
        size--;
        temp = array[0];
        for(int i = 1; i < size + 1; i++){
            array[i-1] = array[i];
        }
        return temp;
    }

    /**
     * Returns the item least recently added to the queue.
     *
     * @return the item least recently added to the queue
     */
    public T peek()
    {
        if (size < 0 || array[0] == null)
        {
            throw new IllegalStateException(); 
        } 
        else 
        {
            return array[0];
        }
    }

    /**
     * Returns whether the queue is empty.
     *
     * @return whether the queue is empty
     */
    public boolean isEmpty()
    {
        if (size <= 0)
        {
            return true;
        }
        return false; 
    }

    /**
     * Returns the number of items in the queue.
     *
     * @return the number of items in the queue
     */
    public int size()
    {
        return size;
    }
}
