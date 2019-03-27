package kunanets.tutors.dto.response;

import kunanets.tutors.entity.Level;
import kunanets.tutors.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class SubjectResponse {
    private Long id;
    private  String name;
    private List<LevelResponse> levels;

    public SubjectResponse (Subject subject){
        id = subject.getId();
        name = subject.getName();
        levels = subject.getLevels().stream().map(LevelResponse::new).collect(Collectors.toList());

    }


}
