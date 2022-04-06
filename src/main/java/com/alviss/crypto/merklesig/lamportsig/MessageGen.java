package com.alviss.crypto.merklesig.lamportsig;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MessageGen {
	
	private String message;

	public MessageGen(String message) {
		this.message = message;
	}

	private byte[] hashMessage () throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] msgInBytes = message.getBytes(StandardCharsets.UTF_8);
		
		return md.digest(msgInBytes);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		MessageGen mg = new MessageGen("DOOM");
		System.out.println(Arrays.toString(mg.hashMessage()));
//		System.out.println(Arrays.equals(null, null));
	}
}
