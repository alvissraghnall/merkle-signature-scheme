package com.alviss.crypto.merklesig.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class MerkleTree {
  
  private Node root;  
  private final List<Leaf> leaves;
  private final MessageDigest messageDigest;
    
  public static class Node{    
    private Node left;
    private Node right;
    private byte[] hash;
        
    public Node(Node left, Node right, byte[] hash){    
      this.hash = hash;
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
  
  public static class Leaf extends Node {

	public Leaf(byte[] data) {
		super(null, null, data);
	}
	  
  }
    
  
  public MerkleTree(List<Leaf> leaves, MessageDigest digest) {
	  this.leaves = leaves;
	  this.messageDigest = digest;
  }
  
  public Node getRoot () {
	  List<Node> nodes = new ArrayList<>(leaves);
	  while(nodes.size() > 1) {
		  nodes = getNextLevel(nodes);
	  }
	  return nodes.get(0);
  }
  
  
  private List<Node> getNextLevel(List<Node> nodes) {
	  List<Node> nextLevel = new ArrayList<>();
	  for (int i = 0; i < nodes.size(); i += 2) {
		Node left = nodes.get(i);
		Node right = (i + 1 < nodes.size()) ? nodes.get(i + 1) : left;
		
		byte[] hash = messageDigest.digest(ByteBuffer.allocate(left.getHash().length + right.getHash().length).put(left.getHash()).put(right.getHash()).array());
		nextLevel.add(new Node(left, right, hash));
		
	  }
	
	  return nextLevel;
	  
  }
  
  private static String toHex(byte[] bytes) {
	  StringBuilder sb = new StringBuilder();
	  for (byte b : bytes) {
		  sb.append(String.format("%02x", b));
	  }
	  return sb.toString();
  }

//public static Node generateTree(ArrayList<byte[]> dataBlocks) {
//          ArrayList<Node> childNodes = new ArrayList<>();
//  
//          for (byte[] digest : dataBlocks) {
//              childNodes.add(new Node(null, null, digest));
//          }
//  
//          return buildTree(childNodes);
//     
//  }  
//  
//  public static Node buildTree(ArrayList<Node> children) {
//          ArrayList<Node> parents = new ArrayList<>();
//  while (children.size() != 1) {
//              int index = 0, length = children.size();
//              while (index < length) {
//                  Node leftChild = children.get(index);
//                  Node rightChild = null;
//  
//                  if ((index + 1) < length) {
//                      rightChild = children.get(index + 1);
//                  } else {
//                      rightChild = new Node(null, null, leftChild.getHash());
//                  }
//                  
//                  ByteArrayOutputStream outputStream = new ByteArrayOutputStream(leftChild.getHash().length + rightChild.getHash().length);
//                 outputStream.write(leftChild.getHash());
//                 outputStream.write(rightChild.getHash());
//                 
//                 byte concatted[] = outputStream.toByteArray();
//                 
//  
//                  byte[] parentHash = HashUtil.generateHash(concatted);
//                  parents.add(new Node(leftChild, rightChild, parentHash));
//                  index += 2;
//              }
//              children = parents;
//              parents = new ArrayList<>();
//          }
//          return children.get(0);
//  }
//  
//  private static void printLevelOrderTraversal(Node root) {
//          if (root == null) {
//              return;
//          }
//  
//          if ((root.getLeft() == null && root.getRight() == null)) {
//              System.out.println(root.getHash());
//          }
//          Queue<Node> queue = new LinkedList<>();
//          queue.add(root);
//          queue.add(null);
//  
//          while (!queue.isEmpty()) {
//              Node node = queue.poll();
//              if (node != null) {
//                  System.out.println(node.getHash());
//              } else {
//                  System.out.println();
//                  if (!queue.isEmpty()) {
//                      queue.add(null);
//                  }
//              }
//  
//              if (node != null && node.getLeft() != null) {
//                  queue.add(node.getLeft());
//              }
//  
//              if (node != null && node.getRight() != null) {
//                  queue.add(node.getRight());
//              }
//  
//          }
//          
//  
//      }

}
