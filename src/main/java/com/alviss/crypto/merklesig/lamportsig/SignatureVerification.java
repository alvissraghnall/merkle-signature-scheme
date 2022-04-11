package com.alviss.crypto.merklesig.lamportsig;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignatureVerification {

	private String message;
	
	private byte[][] signature;
	
	private byte[][][] publicKey;
	
	public SignatureVerification(String message,
			byte[][] signature,
			byte[][][] publicKey) {
		this.message = message;
		this.signature = signature;
		this.publicKey = publicKey;
	}
	
	private boolean verifySig() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] msgInBinaryString = this.hashMessage();
		byte[][][] hashedSig = new byte[2][256][32];
		for (byte[][] s : hashedSig) {
			
		}
		
		return false;
	}
	
	private byte[] hashMessage () throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] msgInBytes = message.getBytes(StandardCharsets.UTF_8);
		
		return md.digest(msgInBytes);
	}
}
