package kunanets.tutors.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TutorRequest {
    @NotNull
    @NotBlank
    private String name;

    @Email
    private String email;

    @Max(1000)
    private Integer price;

    @NotNull
    @NotBlank
    private String photo;

//    @ManyToMany
//    private Set<Subject> subjects = new HashSet<>();

    private List<Long> subjectsId = new ArrayList<>();

}
