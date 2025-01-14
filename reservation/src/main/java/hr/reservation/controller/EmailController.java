package hr.reservation.controller;

import hr.reservation.domain.AuthCodeGenerator;
import hr.reservation.domain.error.Constants;
import hr.reservation.domain.error.ErrorResult;
import hr.reservation.service.mail.AuthCodeService;
import hr.reservation.service.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    AuthCodeService authCodeService;

    @Autowired
    EmailService emailService;

    private static final long AUTH_CODE_TIMEOUT = 180000; // 3분

    @PostMapping("/send")
    public ErrorResult sendAuthCode(@RequestParam String email) {
        String authCode = AuthCodeGenerator.generateAuthCode(6);
        authCodeService.saveAuthCode(email, authCode, AUTH_CODE_TIMEOUT);

        String subject = "예약 확인 인증번호";
        String text = "인증번호 : " + authCode;

        emailService.sendEmail(email, subject, text);

        return new ErrorResult(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG);
    }

    @PostMapping("/verify")
    public ErrorResult verifyAuthCode(@RequestParam String email, @RequestParam String inputCode) {
        boolean isVerified = authCodeService.vertifyAuthCode(email, inputCode);

        if(isVerified) {
            return new ErrorResult(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG);
        } else {
            return new ErrorResult(Constants.EMAIL_AUTH_ERROR_CODE, Constants.EMAIL_AUTH_ERROR_MSG);
        }
    }
}
