package kunanets.tutors;

import kunanets.tutors.entity.Tutor;
import kunanets.tutors.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TutorsApplication {

    @Autowired
    private TutorRepository tutorRepository;

    @PostConstruct
    public void init(){
//        Tutor tutor= new Tutor();
//        tutor.setName("Olena");
//        tutor.setEmail("olenar@gmail.com");
//
//        Tutor tutor1= new Tutor();
//        tutor1.setName("Ivan");
//        tutor1.setEmail("ivannn@gmail.com");
//
//        tutorRepository.save(tutor);
//        tutorRepository.save(tutor1);

 //       tutorRepository.findAll().forEach(System.out::println);
    }


    public static void main(String[] args) {
        SpringApplication.run(TutorsApplication.class, args);
    }


}

