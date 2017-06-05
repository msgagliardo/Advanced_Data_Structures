public class BinarySearchTree implements Cloneable {

	private BinaryTreeNode root;

	public BinarySearchTree() {
		root = null;
	}

	// Change or set ROOT of the tree to a given node(newroot)
	public void setRoot(BinaryTreeNode newroot) {
		root = newroot;
	}

	public BinaryTreeNode getRoot() {
		return root;
	}

	public void add(int element) {
		if (root == null)
			root = new BinaryTreeNode(element, null, null);
		else {
			boolean done = false;
			BinaryTreeNode cursor = root;
			while (!done) {
				if (element <= cursor.getData()) {
					if (cursor.getLeft() != null)
						cursor = cursor.getLeft();
					else {
						cursor.setLeft(new BinaryTreeNode(element, null, null));
						done = true;
					}
				}else {
					if (cursor.getRight() != null)
						cursor = cursor.getRight();
					else {
						cursor.setRight(new BinaryTreeNode(element, null, null));
						done = true;
					}
				}
			}
		}
	}

	public void addAll(BinarySearchTree addend) {
		if (addend == null)
			throw new IllegalArgumentException("addend argument cannot be null");

		addTree(addend.root);
	}

	private void addTree(BinaryTreeNode addroot) {
		if (addroot == null)
			return;

		BinaryTreeNode treeCopy = BinaryTreeNode.treeCopy(addroot);
		while (treeCopy != null) {
			add(treeCopy.getLeftmostData());
			treeCopy = treeCopy.removeLeftmost();
		}
	}

	public long countOccurrences(int target) {
		long answer = 0;
		BinaryTreeNode cursor = root;
		boolean done = false;

		if (root == null)
			return answer;
		else {
			while (!done) {

				if (target == cursor.getData()) {
					if (cursor.getLeft() == null) {
						answer++;
						done = true;
					}else {
						answer++;
						cursor = cursor.getLeft();
					}
				}else if (target < cursor.getData()) {
					if (cursor.getLeft() == null)
						done = true;
					else
						cursor = cursor.getLeft();
				}else {
					if (cursor.getRight() == null)
						done = true;
					else
						cursor = cursor.getRight();
				}
			}
			return answer;
		}
	}

	public boolean remove(int target) {
		if (root == null)
			return false;

		BinaryTreeNode rootCopy = root;

		while (true) {
			if (rootCopy.getLeft() != null && target < rootCopy.getData()) {
				if (target < rootCopy.getLeft().getData() || target > rootCopy.getLeft().getData())
					rootCopy = rootCopy.getLeft();
				else {
					BinaryTreeNode leftTree = rootCopy.getLeft();
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
			else if (rootCopy.getRight() != null && target > rootCopy.getData()) {
				if (target < rootCopy.getRight().getData() || target > rootCopy.getRight().getData())
					rootCopy = rootCopy.getRight();
				else {
					BinaryTreeNode rightTree = rootCopy.getRight();
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
			else if (target == root.getData()) {
				BinaryTreeNode rightTree;

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

	public int size() {
		return BinaryTreeNode.treeSize(root);
	}
}
