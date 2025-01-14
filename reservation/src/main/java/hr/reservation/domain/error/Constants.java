package hr.reservation.domain.error;

// 에러 코드 및 메세지
public class Constants {
    public static final String SUCCESS_CODE = "2000";
    public static final String SUCCESS_MSG = "성공";

    /* CODE */
    public static final String BAD_PARAMETER_CODE = "3001";
    public static final String BAD_REQUEST_CODE = "4001";
    public static final String RESERVATION_CAPA_EXCEED_CODE = "9001";
    public static final String PASSWORD_ENCDEC_CODE = "9002";
    public static final String EMAIL_AUTH_ERROR_CODE = "9003";
    public static final String UNKNOWN_CODE = "9999";

    /* MESSAGE */
    public static final String BAD_PARAMETER_MSG = "파라미터 요청이 잘못되었습니다.";
    public static final String BAD_REQUEST_MSG = "잘못된 요청입니다.";
    public static final String RESERVATION_CAPA_EXCEED_MSG = "예약 가능 인원을 초과하였습니다.";
    public static final String PASSWORD_ENCDEC_MSG = "비밀번호 암복호화 오류 발생하였습니다.";
    public static final String EMAIL_AUTH_ERROR_MSG = "이메일 인증 오류 발생하였습니다.";
    public static final String UNKNOWN_MSG = "오류 발생(UNKNOWN)";
}
