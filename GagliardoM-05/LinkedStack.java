import java.util.EmptyStackException;

public class LinkedStack<E> {

	private Node<E> top;
	
	public LinkedStack() {
		top = null;
	}
	public void push(E item) {
		top = new Node<E>(item, top);
	}
	public E pop() {
		E result;
		
		if (top == null)
			throw new EmptyStackException();
		
		result = top.getData();
		top = top.getLink();
		return result;
	}
	public E peek() {
		E result;
		
		if (top == null)
			throw new EmptyStackException();
		
		result = top.getData();
		return result;
	}
	public boolean isEmpty() {
		if (top == null)
			return true;
		else
			return false;
	}
	public int size() {
		return Node.listLength(top);
	}
}
