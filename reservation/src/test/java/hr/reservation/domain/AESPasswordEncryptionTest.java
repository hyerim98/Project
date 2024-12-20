package hr.reservation.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AESPasswordEncryptionTest {
    String password = "qwer1234!!";

    @Test
    void encrypt() {
        // 암호화
        String encryptedText = AESPasswordEncryption.encrypt(password);
        System.out.println("암호화된 텍스트: " + encryptedText);
    }

    @Test
    void decrypt() {
        // 복호화
        String decryptedText = AESPasswordEncryption.decrypt("PHkpoACwkQjoM22eIXlsGQ==");
        System.out.println("복호화된 텍스트: " + decryptedText);
    }
}