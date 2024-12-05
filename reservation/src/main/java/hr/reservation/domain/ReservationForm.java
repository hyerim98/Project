package hr.reservation.domain;

import hr.reservation.valid.ValidPhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReservationForm {
    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    @ValidPhoneNumber
    private String phone;

    @NotNull // 숫자는 NotBlank, NotEmpty 사용 불가능
    @Min(1)
    private Integer people;

    @NotBlank
    private String date;

    @NotBlank
    private String time;
}
