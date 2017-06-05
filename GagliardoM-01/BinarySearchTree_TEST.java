import java.io.*;
import java.util.Scanner;


public class BinarySearchTree_TEST {

	public static BinaryTreeNode readTreeFromTextFile(String fileName) throws IOException {
		Scanner sc = new Scanner(new File(fileName));
		BinarySearchTree bst = new BinarySearchTree();
		int token;

		while (sc.hasNextInt()) {
			token = sc.nextInt();
			System.out.print(token + "\n\n");
			bst.add(token);
		}
		sc.close();
		return bst.getRoot();

	}

	public static void writeNodeToTextFile(BinaryTreeNode r, PrintWriter output) throws IOException{
		output.print(r.getData() + " ");
		if (r.getLeft() != null)
			writeNodeToTextFile(r.getLeft(), output);
		if (r.getRight() != null)
			writeNodeToTextFile(r.getRight(), output);
	}

	public static void writeTreeToTextFile(BinaryTreeNode r, String fileName) throws IOException {
		PrintWriter outFile = new PrintWriter(fileName);
		writeNodeToTextFile(r, outFile);
		outFile.close();
	}

	public static void main(String[] args)throws IOException{
		Scanner sc = new Scanner(System.in);

		System.out.println("1) Input (read) treeA from a text file:\n");
		BinaryTreeNode btnA = BinarySearchTree_TEST.readTreeFromTextFile("treeA.txt");
		System.out.println();

		System.out.println("2) print treeA:\n");
		System.out.println("Preorder treeA:");
		btnA.preorderPrint();
		System.out.println();
		System.out.println("Inorder treeA:");
		btnA.inorderPrint();
		System.out.println();
		System.out.println("Postorder treeA:");
		btnA.postorderPrint();

		System.out.print("\n\n");

		System.out.println("Display treeA: (with indentation)");
		btnA.print(10);
		System.out.println("\n");

		System.out.println("3) Input (read) treeB from a text file:\n");
		BinaryTreeNode btnB = BinarySearchTree_TEST.readTreeFromTextFile("treeB.txt");
		System.out.println();

		System.out.println("4) Display treeB: (with indentation)");
		btnB.print(10);
		System.out.println("\n");

		BinaryTreeNode btnC = BinaryTreeNode.treeCopy(btnA);
		BinarySearchTree bstC = new BinarySearchTree();
		bstC.setRoot(btnC);
		BinarySearchTree bstB = new BinarySearchTree();
		bstB.setRoot(btnB);
		bstC.addAll(bstB);
		System.out.println("5) The treeB has been added to treeA => tree C");
		System.out.println("Writing this tree to treeC.txt...\n");
		writeTreeToTextFile(btnC, "treeC.txt");
		System.out.println("Display treeC: (with indentation)");
		btnC.print(10);
		System.out.println("\n");

		System.out.print("6) Enter the value of node Z to search in treeC: \n");
		int searchValue = sc.nextInt();
		long occurrences = bstC.countOccurrences(searchValue);
		System.out.println("    " + searchValue + " appears " + occurrences
							+ " time" + (occurrences != 1 ? "s": "") + " in treeC");
		System.out.println("\n");

		System.out.println("7) Display treeD (a copy of treeC):");
		BinaryTreeNode btnD = BinaryTreeNode.treeCopy(btnC);
		btnD.print(10);
		System.out.println("\n");

		BinarySearchTree bstD = new BinarySearchTree();
		bstD.setRoot(btnD);
		System.out.println("8) treeD's size = " + bstD.size());
		System.out.println("\n");

		System.out.print("9) Enter a value X for the new node to be added: \n");
		int nodeValue = sc.nextInt();
		bstC.add(nodeValue);
		System.out.println("   Display the new treeC after adding " + nodeValue + ":\n");
		btnC.print(10);
		System.out.println("\n");

		System.out.print("10) Enter the value Y for the node to be removed: \n");
		int removedValue = sc.nextInt();
		boolean result =bstD.remove(removedValue);
		System.out.println("   Result of removing: " + result);
		System.out.println("   Display the new treeD after removing " + removedValue + ":\n");
		btnD.print(10);
		System.out.println("\n");

		sc.close();
	}
}
