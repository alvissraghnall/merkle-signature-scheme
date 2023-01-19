package com.alviss.crypto.merklesig.lamportsig.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PublicKey {
	
	byte[][][] key;
	
	
	public PublicKey(byte[][][] key) {
		this.key = key;
	}


	public static PublicKey generate(PrivateKey privKey) {
		byte[][][] pubKey = new byte[2][256][32];
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			for(byte i = 0; i < privKey.getPrivateKey().length; i++) {
				for (int j = 0; j < privKey.getPrivateKey()[i].length; j++) {
					 md.update(privKey.getPrivateKey()[i][j]);
//					 System.out.println(Arrays.toString(privKey[i][j]));
					 pubKey[i][j] = md.digest();
				}
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new PublicKey(pubKey);
	}


	public byte[][][] getKey() {
		return key;
	}


	public void setKey(byte[][][] key) {
		this.key = key;
	}

}
