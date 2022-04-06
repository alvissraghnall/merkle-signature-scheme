package com.alviss.crypto.merklesig.lamportsig;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignatureGen {
	
	private String message;

	public SignatureGen(String message) {
		this.message = message;
	}
	
	private String digestToBinary (byte[] digest) {
		BigInteger binaryStrInt = new BigInteger(digest);
		return binaryStrInt.toString(2);
	}
	
	private String toBinary(byte digest[]) {
		StringBuilder sb = new StringBuilder(digest.length * Byte.SIZE);
		
		for(int i = 0; i < digest.length * Byte.SIZE; i++) {
			sb.append((digest[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
		}
		return sb.toString();
	}
	
	private String getBytes(byte[] digest) {
		StringBuilder sb = new StringBuilder();
		for( byte b : digest ) {
			sb.append(Integer.toBinaryString(b & 255 | 256).substring(1));
		}
		return sb.toString();
	}

	private byte[] hashMessage () throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] msgInBytes = message.getBytes(StandardCharsets.UTF_8);
		
		return md.digest(msgInBytes);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		SignatureGen mg = new SignatureGen("DOOM");
		byte[] hash = mg.hashMessage();
		System.out.println(Arrays.toString(hash));
		System.out.println(mg.digestToBinary(hash));
		System.out.println(mg.toBinary(hash));
		System.out.println(mg.getBytes(hash));
	}
}
