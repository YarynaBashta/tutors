package kunanets.tutors.repository;

import kunanets.tutors.entity.Subject;
import kunanets.tutors.entity.Tutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorRepository extends JpaRepository<Tutor,Long>, JpaSpecificationExecutor <Tutor>{
//    List<Tutor> findByPrice(Double from, Double to);
//
//    Page<Tutor> findAllBySubject(Subject subject, Pageable pageable);

}
