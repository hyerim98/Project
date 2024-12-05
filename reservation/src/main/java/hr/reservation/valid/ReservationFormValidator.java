package hr.reservation.valid;

import hr.reservation.domain.Constants;
import hr.reservation.domain.ReservationForm;
import hr.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// 사용X - 추후에 삭제
@Component
@RequiredArgsConstructor
public class ReservationFormValidator implements Validator {
    private final ReservationRepository reservationRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        // ReservationForm 객체인지 확인
        return ReservationForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReservationForm form = (ReservationForm) target;
        String email = form.getEmail();
        String phone = form.getPhone();
        String date = form.getDate();
        String time = form.getTime();

        // 검증 로직
        if(!StringUtils.hasText(form.getName())) {
            errors.rejectValue("name", "required");
        }
        if(!StringUtils.hasText(email)) {
            errors.rejectValue("email", "required");
        }
        if(!StringUtils.hasText(phone)) {
            errors.rejectValue("phone", "required");
        }
        if(!StringUtils.hasText(date)) {
            errors.rejectValue("date", "required");
        }
        if(!StringUtils.hasText(time)) {
            errors.rejectValue("time", "required");
        }

        if(!phone.matches(Constants.PHONE_REGEX)) {
            errors.rejectValue("phone", "valid.reservationForm.phone");
        }
        if(!email.matches(Constants.EMAIL_REGEX)) {
            errors.rejectValue("email", "valid.reservationForm.email");
        }

        /*int remainTicket = reservationRepository.getDateMap(date).get(time);
        if(form.getPeople() <= 0 || form.getPeople() > remainTicket) {
            errors.rejectValue("people", "minMax.reservationForm.people", new Object[]{1, remainTicket}, null);
        }*/
    }
}
