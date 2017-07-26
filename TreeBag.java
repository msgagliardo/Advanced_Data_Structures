public class TreeBag<E extends Comparable<E>> implements Cloneable {
	private BTNode<E> root;
	
	public TreeBag() {
		root = null;
	}
	public void add(E element) {
		if (root == null) 
			root = new BTNode<E>(element, null, null);
		else {
			boolean done = false;
			BTNode<E> cursor = root;
			while (!done) {
				if ((element.compareTo(cursor.getData()) == 0
						|| element.compareTo(cursor.getData()) == -1)
							&& cursor.getLeft() != null)
					
					cursor = cursor.getLeft();
			
				if ((element.compareTo(cursor.getData()) == 0
						|| element.compareTo(cursor.getData()) == -1)
							&& cursor.getLeft() == null) {
				
					cursor.setLeft(new BTNode<E>(element, null, null));
					done = true;
				}
				if ((element.compareTo(cursor.getData()) == 1)
						&& cursor.getRight() != null)
					
					cursor = cursor.getRight();
				
				if ((element.compareTo(cursor.getData()) == 1)
						&& cursor.getRight() == null) {
				
					cursor.setRight(new BTNode<E>(element, null, null));
					done = true;
				}
			}
		
		}
	}
	@SuppressWarnings("unchecked")
	public void addMany(E... elements) {
		if (root == null) 
			root = new BTNode<E>(elements[0], null, null);
		
		if (elements.length > 1) {
			BTNode<E> cursor = root;
			
			for (int i = 1; i < elements.length; i++) {
				while (true) {
					if ((elements[i].compareTo(cursor.getData()) == 0
							|| elements[i].compareTo(cursor.getData()) == -1)
								&& cursor.getLeft() != null)
						
						cursor = cursor.getLeft();
				
					if ((elements[i].compareTo(cursor.getData()) == 0
							|| elements[i].compareTo(cursor.getData()) == -1)
								&& cursor.getLeft() == null) {
					
						cursor.setLeft(new BTNode<E>(elements[i], null, null));
						cursor = root;
						break;
					}
					if ((elements[i].compareTo(cursor.getData()) == 1)
							&& cursor.getRight() != null)
						
						cursor = cursor.getRight();
					
					if ((elements[i].compareTo(cursor.getData()) == 1)
							&& cursor.getRight() == null) {
					
						cursor.setRight(new BTNode<E>(elements[i], null, null));
						cursor = root;
						break;
					}
				}
			}
		}
	}
	/*
	// doing this method recursively won't work because the addAll method takes
	// a TreeBag<E> object as its parameter and not the recursive data structure
	// BTNode<E> 
	public void addAll(TreeBag<E> addend) {
		if (addend == null || addend.root == null)
			throw new IllegalArgumentException("There are no items in addend");
		
		//BTNode<E> cursor = addend.root;
		//addend.root = addend.root.getLeft();
		add(addend.root.getData());
		
		
		if (addend.root.getLeft() != null) {
			addend.root = addend.root.getLeft();
			addAll(addend);
		}
		if (addend.root.getRight() != null) {
			addend.root = addend.root.getRight();
			addAll(addend);
		}
	}
	*/
	public void addAll(TreeBag<E> addend) {
		if (addend == null)
			throw new IllegalArgumentException("There are no items in addend");
		
		while (addend.root != null) {
			add(addend.root.getLeftmostData());
			addend.root = addend.root.removeLeftmost();
		}
		
	}
	public int size() {
		return BTNode.<E>treeSize(root);
	}
	public boolean remove(E target) {
		if (root == null)
			return false;
		
		BTNode<E> rootCopy = root;
		
		while (true) {
			if (rootCopy.getLeft() != null && target.compareTo(rootCopy.getData()) == -1) {
				if (target.compareTo(rootCopy.getLeft().getData()) != 0)
					rootCopy = rootCopy.getLeft();
				else {
					BTNode<E> leftTree = rootCopy.getLeft();
					if (leftTree.getRight() != null) {
						leftTree.setData(leftTree.getRight().getLeftmostData());
						leftTree.setRight(leftTree.getRight().removeLeftmost());
						rootCopy.setLeft(leftTree);
						return true;
					}
					else {
						rootCopy.setLeft(leftTree.getLeft());
						return true;
					}
				}
			}
			else if (rootCopy.getRight() != null && target.compareTo(rootCopy.getData()) == 1) {
				if (target.compareTo(rootCopy.getRight().getData()) != 0)
					rootCopy = rootCopy.getRight();
				else {
					BTNode<E> rightTree = rootCopy.getRight();
					if (rightTree.getRight() != null) {
						rightTree.setData(rightTree.getRight().getLeftmostData());
						rightTree.setRight(rightTree.getRight().removeLeftmost());
						rootCopy.setRight(rightTree);
						return true;
					}
					else {
						rootCopy.setRight(rightTree.getLeft());
						return true;
					}
				}		
			}
			else if (target.compareTo(root.getData()) == 0) {
				BTNode<E> rightTree;
				
				if (root.getRight() != null) {
					root.setData(root.getRight().getLeftmostData());
					rightTree = root.getRight().removeLeftmost();
					root.setRight(rightTree);
					return true;
				}else {
					root = root.getLeft();
					return true;
				}
			}
			else
				return false;
		}
	}
	public static void main(String[] args) {
		TreeBag<Integer> tb1 = new TreeBag<Integer>();
		tb1.addMany(50,30,70,40,68,10,72);
		
		TreeBag<Integer> tb2 = new TreeBag<Integer>();
		tb2.addMany(16,55,88,2,93);
		//tb2.root.print(0);
		
		tb1.addAll(tb2);
		System.out.println(tb1.remove(40));
		System.out.println(tb1.remove(10));
		System.out.println(tb1.remove(50));
		System.out.println(tb1.remove(2));
		System.out.println(tb1.remove(93));
		System.out.println(tb1.remove(68));
		System.out.println(tb1.remove(72));
		System.out.println(tb1.remove(32));
		System.out.println(tb1.remove(90));
		
		tb1.root.print(0);
	}
}
