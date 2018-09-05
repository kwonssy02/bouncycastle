package bouncycastleTest;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;

public class AESCrypto {

	private static String algorithm = "AES/CBC/PKCS5Padding";
	//	private static String secretKey = "12345678901234567890123456789012";
	private static String secretKey = "1234567890123456";
	private static String iv = "1234567890123456";

	// base64는 아스키 코드 내로 표현가능한 인코딩 방식
	private static final String ASCII = "US-ASCII";

	// 문자인코딩 방식
	private final String charset = "UTF-8";

	public String encrypt(String plainText) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		// 암호화 키 생성
		byte[] keyData = secretKey.getBytes(ASCII);
		SecretKey secureKey = new SecretKeySpec(keyData, "AES");

		// cipher instance 초기화
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(iv.getBytes(ASCII)));

		// Encode with UTF-8
		byte[] encodedBytes = plainText.getBytes(charset);

		// Encrypt
		byte[] encryptedBytes = cipher.doFinal(encodedBytes);

		// Encode with Base64
		String encrypted = new String(Base64.encode(encryptedBytes));

		return encrypted;
	}

	public String decrypt(String encrypted) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		// 암호화 키 생성 - keyEncoder를 이용
		byte[] keyData = secretKey.getBytes(ASCII);
		SecretKey secureKey = new SecretKeySpec(keyData, "AES");

		// cipher instance 초기화
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(iv.getBytes(ASCII)));

		// Decode with Base64
		byte[] decodedBytes = Base64.decode(encrypted);

		// Decrypt
		byte[] plainBytes = cipher.doFinal(decodedBytes);

		// Decode with UTF-8
		String decrypted = new String(plainBytes, charset);

		return decrypted;
	}
}
