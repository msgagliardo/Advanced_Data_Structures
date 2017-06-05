
public class Node<E> {

	private E data;
	private Node<E> link;
	
	public Node(E data, Node<E> link) {
		this.data = data;
		this.link = link;
	}
	public E getData() {
		return data;
	}
	public Node<E> getLink() {
		return link;
	}
	public void setData(E data) {
		this.data = data;
	}
	public void setLink(Node<E> link) {
		this.link = link;
	}
	public static <E> int listLength(Node<E> head) {
		int result = 0;
		Node<E> cursor;
		
		if (head == null)
			return 0;
		
		for (cursor = head; cursor != null; cursor = cursor.link)
			result++;
		
		return result;
	}
	public void addNodeAfter(E element) {
		Node<E> newNode = new Node<E>(element, this.link);
		this.link = newNode;
	}
	public void removeNodeAfter() {
		if (link == null)
			return;
		
		link = link.link;
	}
	
}
