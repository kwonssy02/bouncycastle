package bouncycastleTest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashMng {

	public byte[] generateSHA256(String plainText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();

		return digest.digest(plainText.getBytes("UTF-8"));
	}
}
