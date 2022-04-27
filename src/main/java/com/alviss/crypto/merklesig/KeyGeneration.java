package com.alviss.crypto.merklesig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.alviss.crypto.merklesig.lamportsig.KeyGen;
import com.alviss.crypto.merklesig.lamportsig.KeyValue;

public class KeyGeneration {
	
	List<KeyValue> keypairs;

	public KeyGeneration (int N) {
		this.keypairs = new ArrayList<>();
		KeyGen keygen = new KeyGen();
		for (int i = 0; i < N; i++) {
			keypairs.add(keygen.generateKeys());
		}
	}
	
	private List<KeyValue> hashPubKeys () {
		MessageDigest md;
		List<byte[]> hashedPubKeys = new ArrayList<>();
		try {
			md = MessageDigest.getInstance("SHA-256");
			for (KeyValue keypair : this.keypairs) {
				
			}
			
		} catch (NoSuchAlgorithmException exp) {
			exp.printStackTrace();
		}
	}
	
}
