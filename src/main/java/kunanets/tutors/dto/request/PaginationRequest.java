package kunanets.tutors.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter @Setter
public class PaginationRequest {
    private Integer size;

    private Integer page;

    private SortRequest sort;

    public PageRequest mapToPageRequest() {
        PageRequest pageRequest = null;
        if (sort == null) {
            return PageRequest.of(page, size);
        } else {
            return PageRequest.of(page, size, Sort.by(sort.getDirection(), sort.getFieldName()));
        }
    }
}
