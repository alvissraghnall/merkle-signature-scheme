package com.alviss.crypto.merklesig.lamportsig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;

class KeyGen {

	private byte[][][] genPrivate () {
		SecureRandom secRan = new SecureRandom();
		byte[][][] key = new byte[2][256][32];
//		System.out.println(Arrays.deepToString(key));
		for (byte[][] i : key) {
			for (byte[] j : i) {
				secRan.nextBytes(j);
			}
		}
		return key;
	}
	
	private byte[][][] genPublic (byte[][][] privKey) {
		byte[][][] pubKey = new byte[2][256][32];
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			for(byte i = 0; i < privKey.length; i++) {
				for (int j = 0; j < privKey[i].length; j++) {
					 md.update(privKey[i][j]);
//					 System.out.println(Arrays.toString(privKey[i][j]));
					 pubKey[i][j] = md.digest();
				}
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pubKey;
	}
	
	public KeyValue generateKeys () {
		byte[][][] privKey = this.genPrivate();
		return new KeyValue(privKey, this.genPublic(privKey));
	}
//	
//	public static void main(String[] args) throws NoSuchAlgorithmException {
//		MessageDigest md = MessageDigest.getInstance("SHA-256");
//		KeyGen keyGen = new KeyGen();
//		byte[][][] priv = keyGen.genPrivate();
//		System.out.println(Arrays.deepToString(priv[1]));
//		System.out.println(String.format("%s - %s - %s", priv.length, priv[0].length, priv[1].length));
//		System.out.println(Arrays.deepToString(priv[1]));
//		System.out.println(Arrays.deepToString(keyGen.genPublic(priv)));
//		System.out.println(keyGen.genPublic(priv)[0][0].length);
//		md.update(priv[0][7]);
//		byte[] digest = md.digest();
//		System.out.println(Arrays.toString(keyGen.genPublic(priv)[0][7]).equals(Arrays.toString(digest)));
//		
//		System.out.println(Arrays.toString(keyGen.genPublic(priv)[0][7]));
//		System.out.println(Arrays.toString(digest));
//	}
}
