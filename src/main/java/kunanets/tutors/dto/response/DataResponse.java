package kunanets.tutors.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse<T> {

    private List<T> content;

    private Integer totalPages;

    private Long totalElements;
}
