/**
 * A last-in-first-out (LIFO) stack of generic items.
 *
 * @param <T> the type of item to store in the stack
 */
public class Stack<T>
{
	private T[] array;
    private int top;
    private int arraySize;
    
    /**
     * Initializes an empty stack.
     */
    public Stack()
    {
    	array = (T[])new Object[1];
        top = -1;
        arraySize = 1;
    	//throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Adds an item to the stack.
     *
     * @param newItem the item to add
     */
    public void push(T newItem)
    {
        if (newItem == null)
        {
            throw new IllegalArgumentException();
        }
        
        if (top + 1 < arraySize ) 
        {
            array[++top] = (T) newItem;
        } 
        else 
        {
            arraySize *= 2;
            T[] newArray = (T[]) new Object[arraySize];
            
            for ( int i = 0; i <= top; i++ )
            {
                newArray[i] = (T) array[i];
                array[i] = null;
            }
            
            newArray[++top] = (T) newItem;
            
            this.array = newArray;
        }
    	//throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Removes and returns the item on the stack that was most recently added.
     *
     * @return the item on the stack that was most recently added
     */
    public T pop()
    {
        if (isEmpty())
        {
            throw new IllegalStateException(); 
        } 
        else 
        {
            // pop the element
            T temp =  (T)array[top];
            array[top--] = null;
            
            // check if you have to resize

            if (size() > 0 && (arraySize / 4 >= size()))
            {
                T[] newArray = (T[]) new Object[arraySize / 2];
                for ( int i = 0; i <= top; i++ )
                {
                    newArray[i] = (T) array[i];
                    array[i] = null;
                }
                
                arraySize /= 2;
                this.array = newArray;
            }
            

            return (T)temp;
        }
    	//throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Returns the item most recently added to the stack.
     *
     * @return the item most recently added to the stack
     */
    public T peek()
    {
        if (arraySize < 0 || array[0] == null)
        {
            throw new IllegalStateException(); 
        } 
        else 
        {
            return (T)array[top];
        }
    	//throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Returns whether the stack is empty.
     *
     * @return whether the stack is empty
     */
    public boolean isEmpty()
    {
        if (top < 0)
        {
            return true;
        }
        return false;
    	//throw new UnsupportedOperationException("Implement me!");
    }

    /**
     * Returns the number of items in the stack.
     *
     * @return the number of items in the stack
     */
    public int size()
    {
        return top + 1;
    	// throw new UnsupportedOperationException("Implement me!");
    }
}
