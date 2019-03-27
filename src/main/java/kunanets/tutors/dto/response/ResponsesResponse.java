package kunanets.tutors.dto.response;

import kunanets.tutors.entity.Order;
import kunanets.tutors.entity.Responses;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ResponsesResponse {
    private Long id;
    private String text;
    private String studentName;
    private String tutorName;


    public ResponsesResponse (Responses responses){
        id = responses.getId();
        text = responses.getText();
        studentName = responses.getStudent().getName();
        tutorName = responses.getTutor().getName();
    }
}
