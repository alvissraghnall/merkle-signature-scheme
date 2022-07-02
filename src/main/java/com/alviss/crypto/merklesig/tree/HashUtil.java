package com.alviss.crypto.merklesig.tree;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class HashUtil {
  
  static byte[] generateHash(byte[] data) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    return md.digest(data);
    
  }
}
