package com.alviss.crypto.merklesig.lamportsig;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.alviss.crypto.merklesig.lamportsig.util.PublicKey;
import com.alviss.crypto.merklesig.lamportsig.util.SignatureUtils;

public class SignatureVerification extends SignatureUtils {

	private String message;
	
	private byte[][] signature;
	
	private PublicKey publicKey;
	
	public SignatureVerification(String message,
			byte[][] signature,
			PublicKey publicKey) {
		this.message = message;
		this.signature = signature;
		this.publicKey = publicKey;
	}
	
	private boolean verifySig() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] msgHash = this.hashMessage(message);
		String msgBinaryString = this.getBytesFromHash(msgHash);
		byte[][] pkSelect = new byte[256][32];
		byte[][] hashedSig = new byte[256][32];
//		for (byte[][] s : hashedSig) {
//			
//		}
		
		for (int i = 0; i < msgBinaryString.length(); i++) {
			pkSelect[i] = msgBinaryString.charAt(i) == '0' ? publicKey.getKey()[0][i] : publicKey.getKey()[1][i];
			
		}
		
		for ( int i = 0; i < signature.length; i++) {
			hashedSig[i] = md.digest(signature[i]);
		}
		
		System.out.println(Arrays.deepToString(pkSelect));
		System.out.println(Arrays.deepToString(hashedSig));
		System.out.println(Arrays.deepEquals(pkSelect, hashedSig));
		
		return Arrays.equals(hashedSig, pkSelect, Arrays::compare);
	}
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		KeyGen keygen = new KeyGen();
		KeyValue keypair = keygen.generateKeys();
		SignatureGen mg = new SignatureGen("DOOM", keypair);
		SignatureVerification verification = new SignatureVerification(
				"DOOM",
				mg.signature(),
				keypair.getPublicKey()
		);
		System.out.println(verification.verifySig());
//		System.out.println(Arrays.deepToString(keypair.getPublicKey().getKey()));
	}
}
