package com.alviss.crypto.merklesig.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.io.ByteArrayOutputStream;

public class BinaryTree {
  
  private Node root;  
    
  public static class Node{    
    private Node left;
    private Node right;
    private byte[] hash;
        
    public Node(Node left, Node right, byte[] hash){    
      this.data = data;    
      this.left = left;    
      this.right = right;    
    }    
    
    public Node getLeft() {
            return left;
        }
    
        public void setLeft(Node left) {
            this.left = left;
        }
    
        public Node getRight() {
            return right;
        }
    
        public void setRight(Node right) {
            this.right = right;
        }
    
        public byte[] getHash() {
            return hash;
        }
    
        public void setHash(byte[] hash) {
            this.hash = hash;
        }
  }    
    
  
  public static Node generateTree(ArrayList<byte[]> dataBlocks) {
          ArrayList<Node> childNodes = new ArrayList<>();
  
          for (byte[] digest : dataBlocks) {
              childNodes.add(new Node(null, null, digest));
          }
  
          return buildTree(childNodes);
     
  }  
  
  public static Node buildTree(ArrayList<Node> children) {
          ArrayList<Node> parents = new ArrayList<>();
  while (children.size() != 1) {
              int index = 0, length = children.size();
              while (index < length) {
                  Node leftChild = children.get(index);
                  Node rightChild = null;
  
                  if ((index + 1) < length) {
                      rightChild = children.get(index + 1);
                  } else {
                      rightChild = new Node(null, null, leftChild.getHash());
                  }
                  
                  ByteArrayOutputStream outputStream = new ByteArrayOutputStream(leftChild.getHash().length + rightChild.getHash().length);
                 outputStream.write(leftChild.getHash());
                 outputStream.write(rightChild.getHash());
                 
                 byte concatted[] = outputStream.toByteArray();
                 
  
                  byte[] parentHash = HashUtil.generateHash(concatted);
                  parents.add(new Node(leftChild, rightChild, parentHash));
                  index += 2;
              }
              children = parents;
              parents = new ArrayList<>();
          }
          return children.get(0);
  }
  
  private static void printLevelOrderTraversal(Node root) {
          if (root == null) {
              return;
          }
  
          if ((root.getLeft() == null && root.getRight() == null)) {
              System.out.println(root.getHash());
          }
          Queue<Node> queue = new LinkedList<>();
          queue.add(root);
          queue.add(null);
  
          while (!queue.isEmpty()) {
              Node node = queue.poll();
              if (node != null) {
                  System.out.println(node.getHash());
              } else {
                  System.out.println();
                  if (!queue.isEmpty()) {
                      queue.add(null);
                  }
              }
  
              if (node != null && node.getLeft() != null) {
                  queue.add(node.getLeft());
              }
  
              if (node != null && node.getRight() != null) {
                  queue.add(node.getRight());
              }
  
          }
  
      }

}
