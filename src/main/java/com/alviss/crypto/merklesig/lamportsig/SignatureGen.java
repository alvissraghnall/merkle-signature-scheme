package com.alviss.crypto.merklesig.lamportsig;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.alviss.crypto.merklesig.lamportsig.util.SignatureUtils;

public class SignatureGen extends SignatureUtils {
	
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
		byte[] messageHash = this.hashMessage(message);
		String msgAsBinaryString = this.getBytesFromHash(messageHash);
		byte[][][] pk = this.keypair.getPrivateKey().getPrivateKey();
		byte[][] sig = new byte[256][32];
		
		for(int i = 0; i < msgAsBinaryString.length(); i++ ) {
			sig[i] = msgAsBinaryString.charAt(i) == '0' ?  pk[0][i] : pk[1][i];
		}
		return sig;
	}
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		KeyGen keygen = new KeyGen();
		KeyValue keypair = keygen.generateKeys();
		SignatureGen mg = new SignatureGen("DOOM", keypair);
		byte[] hash = mg.hashMessage(mg.message);
		System.out.println(Arrays.toString(hash));
		System.out.println(mg.digestToBinary(hash));
		System.out.println(mg.hashToBinaryString(hash).equals(mg.getBytesFromHash(hash)));;
//		System.out.println(mg.getBytesFromHash(hash));
		System.out.println("====");
		System.out.println(Arrays.deepToString(mg.signature()));
		System.out.println(Arrays.toString(mg.signature()[0]));
		System.out.println(Arrays.toString(keypair.getPrivateKey().getPrivateKey()[1][0]));
		System.out.println(Arrays.toString(mg.signature()[0]).equals(Arrays.toString(keypair.getPrivateKey().getPrivateKey()[1][0])));

		System.out.println(Arrays.toString(mg.signature()[1]).equals(Arrays.toString(keypair.getPrivateKey().getPrivateKey()[0][1])));
	}
}
