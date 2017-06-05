/******************************************************************************
* A <CODE>BinaryTreeNode</CODE> provides a node for a binary tree. Each node
* contains a piece of data and references to a left and right child.
* The references to children may be null to indicate
* that there is no child. The reference stored in a node can also be null.

******************************************************************************/
public class BinaryTreeNode {
   // DATA fields of the BinaryTreeNode class:
   //  1. Each node has one integer, stored in the instance variable data.
   //  2. The instance variables left and right are references to the node's left and right children.
   private int data;
   private BinaryTreeNode left;
   private BinaryTreeNode right;

   // Constructor which initializes all three data fields
   public BinaryTreeNode(int initialData, BinaryTreeNode initialLeft, BinaryTreeNode initialRight)
   {
      data = initialData;
      left = initialLeft;
      right = initialRight;
   }

   // Constructor which creates an empty node
   public BinaryTreeNode()
   {
      left = null;
      right = null;
   }

   // Read DATA of the node
   public int getData( )
   {
      return data;
   }

   // Read the LEFT LINK of the node
   public BinaryTreeNode getLeft( )
   {
      return left;
   }

   // Read the RIGHT LINK of the node
   public BinaryTreeNode getRight( )
   {
      return right;
   }

    // WRITE or change data of the node
   public void setData(int newData)
   {
      data = newData;
   }


   // WRITE or change LEFT LINK of the node
   public void setLeft(BinaryTreeNode newLeft)
   {
      left = newLeft;
   }

   // WRITE or change RIGHT LINK of the node
   public void setRight(BinaryTreeNode newRight)
   {
      right = newRight;
   }

   // Check if the given node IS a LEAF ?
   public boolean isLeaf( )
   {
      return (left == null) && (right == null);
   }

   // Read the DATA of the LEFT-MOST node
   public int getLeftmostData( )
   {
      if (left == null)
         return data;
      else
         return left.getLeftmostData( );
   }

   // Read the DATA of the RIGHT-MOST node
   public int getRightmostData( )
   {
      if (right == null)
         return data;
      else
         return right.getRightmostData( );
   }

   // uses an INORDER traversal to PRINT on SCREEN the DATA
   // of each node at or below this node of the binary tree.
   public void inorderPrint( )
   {
      if (left != null)
         left.inorderPrint( );
      System.out.print(data+" ");
      if (right != null)
         right.inorderPrint( );
   }

   // uses an PREORDER traversal to PRINT on SCREEN the DATA
   // of each node at or below this node of the binary tree.
   public void preorderPrint( )
   {
      System.out.print(data+" ");
      if (left != null)
         left.preorderPrint( );
      if (right != null)
         right.preorderPrint( );
   }

   // uses an POSTORDER traversal to PRINT on SCREEN the DATA
   // of each node at or below this node of the binary tree.
   public void postorderPrint( )
   {
      if (left != null)
         left.postorderPrint( );
      if (right != null)
         right.postorderPrint( );
      System.out.print(data+" ");
   }

   // Show DEPTH of nodes
   // uses an INORDER traversal to PRINT on the screen the DATA of each node at or below this node
   // of the binary tree(with INDENTATION to indicate the DEPTH of the nodes
   // a dash "--" is printed at any place where a child has no sibling
   // (the indentation of each line of data is four times its depth in the tree)
   public void print(int depth)
   {
      int i;

      // Print the indentation and the data from the current node:
      for (i = 1; i <= depth; i++)
         System.out.print("    ");
      System.out.println(data);

      // Print the left subtree (or a dash "-" if there is no left child)
      if (left != null)
         left.print(depth+1);
      else if (right != null)
      {
         for (i = 1; i <= depth+1; i++)
            System.out.print("    ");
         System.out.println("--");
      }

      // Print the right subtree (or a dash "-" if there is no left child)
      if (right != null)
         right.print(depth+1);
      else if (left != null)
      {
         for (i = 1; i <= depth+1; i++)
            System.out.print("    ");
         System.out.println("--");
      }
   }


   /**
   * REMOVE the LEFTMOST Node of the tree below this node.
   *   The return value is a reference to the root of the new (smaller) tree.
   *   This return value could be null if the original tree had only one
   *   node (since that one node has now been removed).
   **/
   public BinaryTreeNode removeLeftmost( )
   {
      if (left == null)
         return right;
      else
      {
         left = left.removeLeftmost( );
         return this;
      }
   }

   /**
   * REMOVE the RIGHTMOST Node of the tree below this node.
   *   The return value is a reference to the root of the new (smaller) tree.
   *   This return value could be null if the original tree had only one
   *   node (since that one node has now been removed).
   **/
   public BinaryTreeNode removeRightmost( )
   {
      if (right == null)
         return left;
      else
         return right.removeRightmost( );
   }

   /**
   * COPY a binary tree.
   * The return value is a reference to the root of the copy.
   **/
   public static BinaryTreeNode treeCopy(BinaryTreeNode source)
   {
      BinaryTreeNode leftCopy, rightCopy;

      if (source == null)
         return null;
      else
      {
         leftCopy = treeCopy(source.left);
         rightCopy = treeCopy(source.right);
         return new BinaryTreeNode(source.data, leftCopy, rightCopy);
      }
   }

   /**
   * SIZE of a tree => Count the NUMBER of NODEs in a binary tree.
   **/
   public static int treeSize(BinaryTreeNode root)
   {
      if (root == null)
         return 0;
      else
         return 1 + treeSize(root.left) + treeSize(root.right);
   }

}
