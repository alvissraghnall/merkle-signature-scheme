package com.alviss.crypto.merklesig.lamportsig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;

class KeyGen {

	private byte[][] genPrivate () {
		SecureRandom secRan = new SecureRandom();
		byte[][] key = new byte[2][32];
//		System.out.println(Arrays.deepToString(key));
		for (byte[] i : key) {
			secRan.nextBytes(i);
		}
		return key;
	}
	
	private byte[][][] genPublic (byte[][] privKey) {
		byte[][][] pubKey = new byte[2][32][32];
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			for(byte i = 0; i < privKey.length; i++) {
				for (int j = 0; j < privKey[i].length; j++) {
					 md.update(privKey[i][j]);
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
		return new KeyValue(this.genPrivate()	, this.genPublic(null));
	}
//	
//	public static void main(String[] args) throws NoSuchAlgorithmException {
//		MessageDigest md = MessageDigest.getInstance("SHA-256");
//		KeyGen keyGen = new KeyGen();
//		byte[][] priv = keyGen.genPrivate();
//		System.out.println(Arrays.deepToString(priv));
//		System.out.println(Arrays.deepToString(keyGen.genPublic(priv)));
//		System.out.println(keyGen.genPublic(priv)[0][0].length);
//		md.update(priv[0][0]);
//		System.out.println(Arrays.toString(keyGen.genPublic(priv)[0][0]).equals(Arrays.toString(md.digest())));
//		
//		System.out.println(Arrays.toString(keyGen.genPublic(priv)[0][0]));
//		System.out.println(Arrays.toString(md.digest()));
//	}
}
