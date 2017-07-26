public class BTNode<E> {
	private E data;
	private BTNode<E> left, right;
	
	public BTNode(E initialData, BTNode<E> initialLeft, BTNode<E> initialRight) {
		data = initialData;
		left = initialLeft;
		right = initialRight;
	}
	public E getData() {
		return data;
	}
	public BTNode<E> getLeft() {
		return left;
	}
	public BTNode<E> getRight() {
		return right;
	}
	public void setData(E newData) {
		data = newData;
	}
	public void setLeft(BTNode<E> newLeft) {
		left = newLeft;
	}
	public void setRight(BTNode<E> newRight) {
		right = newRight;
	}
	public E getLeftmostData() {
		if (left == null)
			return data;
		else 
			return left.getLeftmostData();
	}
	public E getRightmostData() {
		if (right == null)
			return data;
		else 
			return right.getRightmostData();
	}
	public boolean isLeaf() {
		return (left == null) && (right == null);
	}
	public BTNode<E> removeLeftmost() {
		if (left == null)
			return right;
		else {
			left = left.removeLeftmost();
			return this;
		}
	}
	public BTNode<E> removeRightmost() {
		if (right == null) 
			return left;
		else {
			right = right.removeRightmost();
			return this;
		}	
	}
	public BTNode<E> treeCopy(BTNode<E> source) {
		BTNode<E> leftCopy, rightCopy;
 		
		if (source == null)
			return null;
		else {
			leftCopy = treeCopy(source.left);
			rightCopy = treeCopy(source.right);
			return new BTNode<E>(source.data, leftCopy, rightCopy);
		}
	}
	public void preorderPrint() {
		System.out.println(data);
		
		if (left != null)
			left.preorderPrint();
		
		if (right != null)
			right .preorderPrint();
	}
	public void inorderPrint() {
		if (left != null)
			left.inorderPrint();
		
		System.out.println(data);
		
		if (right != null)
			right.inorderPrint();
	}
	public void postorderPrint() {
		if (left != null)
			left.postorderPrint();
		
		if (right != null)
			right.postorderPrint();
		
		System.out.println(data);
	}
	public void print(int depth) {
		if (right != null)
			right.print(depth + 1);
		
		for (int i = 0; i < depth; i++) 
			System.out.print("\t");
		
		System.out.println(data);
		
		if (left != null)
			left.print(depth + 1);
	}
	public static <E> int treeSize(BTNode<E> root) {
		if (root == null)
			return 0;
		else 
			return 1 + treeSize(root.left) + treeSize(root.right);
	}
	/*public static <E> void increase(BTNode<E extends Number> root) {
		if (root == null)
			return;
		else {
			root.data += 1;
			increase(root.left);
			increase(root.right);
		}
	}*/
}
