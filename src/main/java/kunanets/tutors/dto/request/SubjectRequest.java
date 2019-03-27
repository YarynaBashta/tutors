package kunanets.tutors.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor

public class SubjectRequest {
    @NotNull
    @NotBlank
    private String name;

//    @ManyToMany
//    private Set<Level> levels = new HashSet<>();

    private List<Long> levelsId = new ArrayList<>();

}
