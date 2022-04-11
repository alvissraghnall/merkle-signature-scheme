package com.alviss.crypto.merklesig.lamportsig;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignatureGen {
	
	private String message;
	private KeyValue keypair;

	public SignatureGen(String message, KeyValue keypair) {
		this.message = message;
		this.keypair = keypair;
	}
	
	private String digestToBinary (byte[] digest) {
		BigInteger binaryStrInt = new BigInteger(digest);
		return binaryStrInt.toString(2);
	}
	
	
	public byte[][] signature () throws NoSuchAlgorithmException {
		byte[] messageHash = this.hashMessage();
		String msgAsBinaryString = this.getBytesFromHash(messageHash);
		byte[][][] pk = this.keypair.getPrivateKey();
		byte[][] sig = new byte[256][32];
		
		for(int i = 0; i < msgAsBinaryString.length(); i++ ) {
			sig[i] = msgAsBinaryString.charAt(i) == '0' ?  pk[0][i] : pk[1][i];
		}
		return sig;
	}
	
	private String hashToBinaryString(byte digest[]) {
		StringBuilder sb = new StringBuilder(digest.length * Byte.SIZE);
		
		for(int i = 0; i < digest.length * Byte.SIZE; i++) {
			sb.append((digest[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
		}
		return sb.toString();
	}
	
	private String getBytesFromHash(byte[] digest) {
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
		KeyGen keygen = new KeyGen();
		KeyValue keypair = keygen.generateKeys();
		SignatureGen mg = new SignatureGen("DOOM", keypair);
		byte[] hash = mg.hashMessage();
		System.out.println(Arrays.toString(hash));
		System.out.println(mg.digestToBinary(hash));
		System.out.println(mg.hashToBinaryString(hash));
		System.out.println(mg.getBytesFromHash(hash));
		System.out.println("====");
		System.out.println(Arrays.toString(mg.signature()[0]));
		System.out.println(Arrays.toString(keypair.getPrivateKey()[1][0]));
		System.out.println(Arrays.toString(mg.signature()[0]).equals(Arrays.toString(keypair.getPrivateKey()[1][0])));

		System.out.println(Arrays.toString(mg.signature()[1]).equals(Arrays.toString(keypair.getPrivateKey()[0][1])));
	}
}
