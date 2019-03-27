package kunanets.tutors.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TutorFilterRequest {

    private  String name;
    private Integer priceFrom;
    private Integer priceTo;

    private List<Long> subjectsId = new ArrayList<>();
    private List<Long> levelsId = new ArrayList<>();

    private PaginationRequest pagination;
}
