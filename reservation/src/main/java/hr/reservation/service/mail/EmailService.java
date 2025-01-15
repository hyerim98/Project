package hr.reservation.service.mail;

import hr.reservation.domain.ReservationForm;
import hr.reservation.domain.error.Constants;
import hr.reservation.domain.error.ErrorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

// 이메일 전송
@Service
public class EmailService {
    @Autowired AuthCodeService authCodeService;
    @Autowired private JavaMailSender mailSender;

    private static final long AUTH_CODE_TIMEOUT = 180000; // 3분

    // 메일 전송
    public void sendAuthCode(String email, ReservationForm form) {
        String strDate = LocalDate.now().getYear() + "년 " + form.getDate().substring(0,2) + "월 " + form.getDate().substring(2) + "일 " + form.getTime().substring(4) + "시";

        String subject = "예약 확정 메일";
        String text = "예약이 확정되었습니다." + System.lineSeparator() +
                "예약번호 : " + form.getReservationId() + System.lineSeparator() +
                "이름 : " + form.getName() + System.lineSeparator() +
                "예약날짜 : " + strDate + System.lineSeparator() +
                "인원 : " + form.getPeople() + "명";


        sendEmail(email, subject, text);

        new ErrorResult(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG);
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
