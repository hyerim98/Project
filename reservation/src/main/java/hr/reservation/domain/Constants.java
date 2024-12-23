package hr.reservation.domain;

public class Constants {
    // 휴대폰 번호 정규식
    public static final String PHONE_REGEX = "^(010|011)-?\\d{3,4}-?\\d{4}$";
    // 이메일 정규식
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
}
