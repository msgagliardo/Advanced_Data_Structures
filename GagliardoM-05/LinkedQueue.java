import java.util.NoSuchElementException;

public class LinkedQueue<E> {

	private Node<E> front;
	
	public LinkedQueue() {
		front = null;
	}
	public void add(E item) {
		if (front == null)
			front = new Node<E>(item, null);
		else
			front.addNodeAfter(item);
	}
	public E remove() {
		if (front == null)
			throw new NoSuchElementException();
		else {
			E result = front.getData();
			front = front.getLink();
			return result;
		}
	}
	public E peek() {
		if (front == null)
			throw new NoSuchElementException();
		else
			return front.getData();
	}
	public boolean isEmpty() {
		return (front == null);
	}
	public int size() {
		return Node.listLength(front);
	}
}
