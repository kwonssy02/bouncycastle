package bouncycastleTest;

public class Main {
    
	public static void main(String[] args) throws Exception {
		String plainText = "테스트 스트링";
		AESCrypto aesCrypto = new AESCrypto();
		String encryptedStr = aesCrypto.encrypt(plainText);
		String decryptedStr = aesCrypto.decrypt(encryptedStr);
		System.out.println(plainText);
		System.out.println(encryptedStr);
		System.out.println(decryptedStr);
	}

	
}
