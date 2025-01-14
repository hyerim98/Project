package hr.reservation.service.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// 인증 번호 저장
@Slf4j
@Service
public class AuthCodeService {
    private final Map<String, AuthCodeData> authCodeStorage = new HashMap<>();

    // 인증 번호 저장(만료시간 포함)
    public void saveAuthCode(String email, String authCode, long timeoutInMillis) {
        long expiryTime = System.currentTimeMillis() + timeoutInMillis;
        authCodeStorage.put(email, new AuthCodeData(authCode, expiryTime));
    }

    //  인증 번호 검증
    public boolean vertifyAuthCode(String email, String inputCode) {
        AuthCodeData data = authCodeStorage.get(email);

        // 인증 번호가 없거나 만료되었는지 확인
        if(data == null || System.currentTimeMillis() > data.getExpiryTime()) {
            return false;
        }

        return data.getCode().equals(inputCode);
    }

    private static class AuthCodeData {
        private final String code;
        private final long expiryTime;

        public AuthCodeData(String code, long expiryTime) {
            this.code = code;
            this.expiryTime = expiryTime;
        }

        public String getCode() {
            return code;
        }

        public long getExpiryTime() {
            return expiryTime;
        }
    }
}
