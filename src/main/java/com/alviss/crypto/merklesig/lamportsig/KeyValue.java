package com.alviss.crypto.merklesig.lamportsig;

import com.alviss.crypto.merklesig.lamportsig.util.PrivateKey;
import com.alviss.crypto.merklesig.lamportsig.util.PublicKey;

public class KeyValue {

	private PrivateKey privateKey;
	
	private PublicKey publicKey;

	protected KeyValue(PrivateKey privateKey, PublicKey publicKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}

	protected PrivateKey getPrivateKey() {
		return privateKey;
	}
//
//	@SuppressWarnings("unused")
//	private void setPrivateKey(byte[][] privateKey) {
//		this.privateKey = privateKey;
//	}

	public PublicKey getPublicKey() {
		return publicKey;
	}
//
//	@SuppressWarnings("unused")
//	private void setPublicKey(byte[][][] publicKey) {
//		this.publicKey = publicKey;
//	}
	
	
}
