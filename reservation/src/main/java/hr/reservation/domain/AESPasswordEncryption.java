package hr.reservation.domain;

import hr.reservation.domain.error.AESPasswordEncryDecException;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class AESPasswordEncryption {
    // 키 생성
    private static final String ALGORITHM = "AES";
    private static final byte[] key = "reservation12345".getBytes(); // 16-byte key (예: 128-bit)

    // 암호화
    public static String encrypt(String plainText) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new AESPasswordEncryDecException("비밀번호 암호화 오류");
        }
    }

    // 복호화
    public static String decrypt(String encryptedText) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            return new String(cipher.doFinal(decodedBytes));
        } catch (Exception e) {
            throw new AESPasswordEncryDecException("비밀번호 복호화 오류");
        }
    }
}
