package com.alviss.crypto.merklesig.lamportsig.util;

import java.security.SecureRandom;

public class PrivateKey  {
	
	byte[][][] key;

	public PrivateKey(byte[][][] privateKey) {
		super();
		this.key = privateKey;
	}
	
	public static PrivateKey generate() {
		SecureRandom secRan = new SecureRandom();
		byte[][][] key = new byte[2][256][32];
//		System.out.println(Arrays.deepToString(key));
		for (byte[][] i : key) {
			for (byte[] j : i) {
				secRan.nextBytes(j);
			}
		}
		return new PrivateKey(key);
	}

	public byte[][][] getPrivateKey() {
		return key;
	}

	private void setPrivateKey(byte[][][] key) {
		this.key = key;
	}
}
