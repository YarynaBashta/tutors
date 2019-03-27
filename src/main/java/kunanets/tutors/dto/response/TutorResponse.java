package kunanets.tutors.dto.response;

import kunanets.tutors.entity.Subject;
import kunanets.tutors.entity.Tutor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
public class TutorResponse {
    private Long id;
    private String name;
    private String email;
    private Integer price;
    private String photo;

    private List<SubjectResponse> subjects;

    public TutorResponse(Tutor tutor) {
       id = tutor.getId();
       name = tutor.getName();
       email = tutor.getEmail();
       price = tutor.getPrice();
       photo = tutor.getPhoto();
       subjects = tutor.getSubjects().stream().map(SubjectResponse::new).collect(Collectors.toList());

    }
}
