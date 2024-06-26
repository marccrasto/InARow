//This is a class that creates a node storing an element and a pointer to the next node.
//Name of author - Marc Alex Crasto
public class LinearNode<E> {
	//stores a pointer to the next node.
    private LinearNode<E> next;
    //stores an element within the node.
    private E element;
    
    //Creates an empty node.
    public LinearNode()
    {
        next = null;
        element = null;
    }
    
    
    //Creates a node storing the specified element.
    public LinearNode (E elem)
    {
        next = null;
        element = elem;
    }
    
    //Returns the node that follows this one.
    public LinearNode<E> getNext()
    {
        return next;
    }
    
    //Sets the node that follows this one.
    public void setNext (LinearNode<E> node)
    {
        next = node;
    }
    
    //Returns the element stored in this node.
    public E getElement()
    {
        return element;
    }
    
    //Sets the element stored in this node.
    public void setElement (E elem)
    {
        element = elem;
    }
}
