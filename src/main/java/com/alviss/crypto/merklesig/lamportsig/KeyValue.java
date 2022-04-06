package com.alviss.crypto.merklesig.lamportsig;

public class KeyValue {

	private byte[][] privateKey;
	
	private byte[][][] publicKey;

	public KeyValue(byte[][] privateKey, byte[][][] publicKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}

	protected byte[][] getPrivateKey() {
		return privateKey;
	}
//
//	@SuppressWarnings("unused")
//	private void setPrivateKey(byte[][] privateKey) {
//		this.privateKey = privateKey;
//	}

	public byte[][][] getPublicKey() {
		return publicKey;
	}
//
//	@SuppressWarnings("unused")
//	private void setPublicKey(byte[][][] publicKey) {
//		this.publicKey = publicKey;
//	}
	
	
}
