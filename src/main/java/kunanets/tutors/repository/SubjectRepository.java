package kunanets.tutors.repository;

import kunanets.tutors.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    @Query("select s from Subject s join fetch s.tutors tr where s.name=:sName")
    Subject findByName(@Param("sName") String name);
}
