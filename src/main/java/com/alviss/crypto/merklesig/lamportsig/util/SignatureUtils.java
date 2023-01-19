package com.alviss.crypto.merklesig.lamportsig.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class SignatureUtils {
	
	public String hashToBinaryString(byte digest[]) {
		StringBuilder sb = new StringBuilder(digest.length * Byte.SIZE);
		
		for(int i = 0; i < digest.length * Byte.SIZE; i++) {
			sb.append((digest[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
		}
		return sb.toString();
	}
	
	public String getBytesFromHash(byte[] digest) {
		StringBuilder sb = new StringBuilder();
		for( byte b : digest ) {
			sb.append(Integer.toBinaryString(b & 255 | 256).substring(1));
		}
		return sb.toString();
	}
	
	public byte[] hashMessage (String message) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] msgInBytes = message.getBytes(StandardCharsets.UTF_8);
		
		return md.digest(msgInBytes);
	}

}
