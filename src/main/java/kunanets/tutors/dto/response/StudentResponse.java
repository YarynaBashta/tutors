package kunanets.tutors.dto.response;

import kunanets.tutors.entity.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponse {
    private Long id;
    private  String name;
    private String phoneNumber;

    public StudentResponse (Student student){
        id = student.getId();
        name = student.getName();
        phoneNumber = student.getPhoneNumber();
    }

}
