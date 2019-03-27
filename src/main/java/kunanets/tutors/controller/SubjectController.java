package kunanets.tutors.controller;

import kunanets.tutors.dto.request.PaginationRequest;
import kunanets.tutors.dto.request.SubjectRequest;
import kunanets.tutors.dto.response.DataResponse;
import kunanets.tutors.dto.response.SubjectResponse;
import kunanets.tutors.dto.response.TutorResponse;
import kunanets.tutors.exeption.WrongInputException;
import kunanets.tutors.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<SubjectResponse> findAll(){
        return subjectService.findAll();
    }

    @GetMapping("/one")
    public SubjectResponse findOne(@RequestParam Long id) throws WrongInputException {
        return subjectService.findOneById(id);
    }
    @PostMapping
    public SubjectResponse save(@RequestBody @Valid SubjectRequest subjectRequest)throws WrongInputException {
        return subjectService.save(subjectRequest);
    }

    @PutMapping
    public SubjectResponse update(@RequestBody @Valid SubjectRequest subjectRequest, @RequestParam Long id) throws WrongInputException {
        return subjectService.update(subjectRequest, id);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) throws WrongInputException {
        subjectService.delete(id);
    }

}
