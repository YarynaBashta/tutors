package kunanets.tutors.dto.response;

import kunanets.tutors.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private LocalDate date;
    private String studentName;
    private String tutorName;

    public OrderResponse (Order order){
        id = order.getId();
        date = order.getDate();
        studentName = order.getStudent().getName();
        tutorName = order.getTutor().getName();
    }
}
