package hr.reservation.domain.error;

// 에러 코드 및 메세지
public class Constants {
    public static final String SUCCESS_CODE = "2000";
    public static final String SUCCESS_MSG = "성공";

    /* CODE */
    public static final String BAD_PARAMETER_CODE = "3001";
    public static final String BAD_REQUEST_CODE = "4001";
    public static final String RESERVATION_CAPA_EXCEED_CODE = "9001";

    /* MESSAGE */
    public static final String BAD_PARAMETER_MSG = "파라미터 요청이 잘못되었습니다.";
    public static final String BAD_REQUEST_MSG = "잘못된 요청입니다.";
    public static final String RESERVATION_CAPA_EXCEED_MSG = "예약 가능 인원을 초과하였습니다.";
}
