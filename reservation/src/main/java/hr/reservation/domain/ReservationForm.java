package hr.reservation.domain;

import hr.reservation.valid.ValidPhoneNumber;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@RequiredArgsConstructor
public class ReservationForm {
    @Size(max = 17)
    private String reservationId;

    @NotBlank
    @Size(max = 10)
    private String name;

    @Email
    @Size(max = 50)
    private String email;

    @NotBlank
    @ValidPhoneNumber
    @Size(max = 13)
    private String phone;

    @NotNull // 숫자는 NotBlank, NotEmpty 사용 불가능
    @Min(1)
    private Integer people;

    @NotBlank
    @Size(max = 4)
    private String date;

    @NotBlank
    @Size(max = 6)
    private String time;

    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "비밀번호는 최소 8자, 하나 이상의 대문자, 소문자, 숫자 및 특수 문자를 포함해야 합니다.")
    private String password;
}
