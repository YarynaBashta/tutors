package kunanets.tutors.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class OrderRequest {


    private LocalDate date;

    private Long studentId;

    private Long tutorId;
}
