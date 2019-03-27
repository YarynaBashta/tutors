package kunanets.tutors.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@NoArgsConstructor
public class StudentRequest {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Pattern(regexp = "^((\\+38|38))?0\\d{9}", message = "NOT_CORRECT_PHONE_NUMBER")
     private String phoneNumber;


}
