package hr.reservation.domain;

import java.util.Random;

// 인증 번호 생성
public class AuthCodeGenerator {
    public static String generateAuthCode(int length) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10)); // 0 ~ 9의 랜덤 숫자 생성
        }

        return code.toString();
    }
}
