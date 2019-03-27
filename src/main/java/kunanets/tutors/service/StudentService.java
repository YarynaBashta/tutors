package kunanets.tutors.service;

import kunanets.tutors.dto.request.PaginationRequest;
import kunanets.tutors.dto.request.StudentRequest;
import kunanets.tutors.dto.response.DataResponse;
import kunanets.tutors.dto.response.StudentResponse;
import kunanets.tutors.entity.Student;
import kunanets.tutors.exeption.WrongInputException;
import kunanets.tutors.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public List<StudentResponse> findAll() {
        return studentRepository.findAll().stream().map(StudentResponse::new).collect(Collectors.toList());
    }
    public Student findOne(Long id) throws WrongInputException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Student with id " + id + " not exists"));
    }
    private Student studentRequestToStudent(StudentRequest request, Student student )throws WrongInputException{
        if(student == null){
            student = new Student();
        }
        student.setName(request.getName());
        student.setPhoneNumber(request.getPhoneNumber());
        return studentRepository.save(student);
    }
    public StudentResponse save(StudentRequest request) throws WrongInputException {
        return new StudentResponse(studentRequestToStudent(request, null));
    }

    public StudentResponse update(StudentRequest request, Long id) throws WrongInputException {
        return new StudentResponse(studentRequestToStudent(request, findOne(id)));
    }


    public void delete(Long id) throws WrongInputException {
        studentRepository.delete(findOne(id));
    }

    public DataResponse<StudentResponse> findAll (PaginationRequest paginationRequest) {
        Page<Student> page = studentRepository.findAll(paginationRequest.mapToPageRequest());
        return new DataResponse<>(
                page.get().map(StudentResponse::new).collect(Collectors.toList()),
                page.getTotalPages(),
                page.getTotalElements());
    }
}
