package kunanets.tutors.controller;


import kunanets.tutors.dto.request.PaginationRequest;
import kunanets.tutors.dto.request.StudentRequest;
import kunanets.tutors.dto.response.DataResponse;
import kunanets.tutors.dto.response.LevelResponse;
import kunanets.tutors.dto.response.StudentResponse;
import kunanets.tutors.exeption.WrongInputException;
import kunanets.tutors.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentResponse> findAll(){
        return studentService.findAll();
    }
    @PostMapping("/page")
    public DataResponse<StudentResponse> getPage(@RequestBody PaginationRequest paginationRequest){
        return studentService.findAll(paginationRequest);
    }


    @PostMapping
    public StudentResponse save(@RequestBody @Valid StudentRequest studentRequest) throws WrongInputException{
        return studentService.save(studentRequest);
    }

    @PutMapping
    public StudentResponse update(@RequestBody @Valid StudentRequest studentRequest, @RequestParam Long id) throws WrongInputException {
        return studentService.update(studentRequest, id);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) throws WrongInputException {
        studentService.delete(id);
    }
}
