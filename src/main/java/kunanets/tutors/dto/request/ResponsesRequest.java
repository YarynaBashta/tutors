package kunanets.tutors.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class ResponsesRequest {
    @NotNull
    @NotBlank
    private String text;

    private Long studentId;

    private Long tutorId;
}
