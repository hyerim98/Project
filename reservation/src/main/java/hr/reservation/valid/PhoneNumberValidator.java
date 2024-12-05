package hr.reservation.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// 휴대폰 번호 유효성 검사
public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    private static final String PHONE_REGEX = "^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Null 값은 허용하지 않는 경우 추가 검증
        if (value == null || value.isEmpty()) {
            return false;
        }
        return value.matches(PHONE_REGEX);
    }
}
