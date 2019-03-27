package kunanets.tutors.specification;

import kunanets.tutors.dto.request.TutorFilterRequest;
import kunanets.tutors.entity.Level;
import kunanets.tutors.entity.Subject;
import kunanets.tutors.entity.Tutor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class TutorSpecification implements Specification<Tutor> {

    private TutorFilterRequest filter;

    public TutorSpecification(TutorFilterRequest filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Tutor> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Predicate byPrice = findByPrice(root, criteriaBuilder);
        if (byPrice != null) predicates.add(byPrice);
//
        Predicate bySubject = findBySubject(root, criteriaBuilder);
        if (bySubject != null) predicates.add(bySubject);

        Predicate byLevels = findByLevels(root, criteriaBuilder);
        if (byLevels != null) predicates.add(byLevels);

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
    private Predicate findBySubject(Root<Tutor> root, CriteriaBuilder criteriaBuilder) {
        Join<Tutor, Subject> subjectJoin = root.join("subjectsId");
        return subjectJoin.get("id").in(filter.getSubjectsId().toArray());
    }

    private Predicate findByLevels(Root<Tutor> root, CriteriaBuilder criteriaBuilder) {
        Join<Tutor, Subject> subjectJoin = root.join("subjectsId");
//        subjectJoin.get("id").in(filter.getSubjectsId().toArray());
        Join<Subject, Level> levelJoin = root.join("levelsId");
        return levelJoin.get("id").in(filter.getLevelsId().toArray());
    }


    private Predicate findByPrice(Root<Tutor> root, CriteriaBuilder criteriaBuilder){
        if (filter.getPriceFrom() == null && filter.getPriceTo()==null) {
            return null;
        }
        if (filter.getPriceFrom() == null) {
            filter.setPriceFrom(0);
        }
        if (filter.getPriceTo()==null) {
            filter.setPriceTo(Integer.MAX_VALUE);
        }
        return criteriaBuilder.between(root.get("price"), filter.getPriceFrom(), filter.getPriceTo());
        }

}
