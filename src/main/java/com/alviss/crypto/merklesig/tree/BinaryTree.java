public class BinaryTree {

public static class Node{    
  byte[][] data;    
  Node left;    
  Node right;    
  public Node(byte[][] data){    
    this.data = data;    
    this.left = null;    
    this.right = null;    
  }    
}    
  
  Node root;
  
  public BinaryTree()  
        {    
          root = null;    
        }  

}
