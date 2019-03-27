package kunanets.tutors.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor

public class LevelRequest {
    @NotNull
    @NotBlank
    private String name;
}
