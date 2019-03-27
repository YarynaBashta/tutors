package kunanets.tutors.controller;


import kunanets.tutors.dto.request.PaginationRequest;
import kunanets.tutors.dto.request.TutorFilterRequest;
import kunanets.tutors.dto.request.TutorRequest;
import kunanets.tutors.dto.response.DataResponse;
import kunanets.tutors.dto.response.LevelResponse;
import kunanets.tutors.dto.response.TutorResponse;
import kunanets.tutors.exeption.WrongInputException;
import kunanets.tutors.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @GetMapping
    public List<TutorResponse> findAll(){
        return tutorService.findAll();
    }

    @PostMapping
    public TutorResponse save(@RequestBody @Valid TutorRequest tutorRequest) throws WrongInputException{
        return tutorService.save(tutorRequest);
    }
    @PostMapping("/page")
    public DataResponse<TutorResponse> getPage(@RequestBody PaginationRequest paginationRequest){
        return tutorService.findAll(paginationRequest);
    }
    @PostMapping("/filter")
    public DataResponse<TutorResponse> findAllByFilter(@RequestBody TutorFilterRequest tutorFilterRequest){
        return  tutorService.filterByFilter(tutorFilterRequest);
    }


    @PutMapping
    public TutorResponse update(@RequestBody @Valid TutorRequest tutorRequest, @RequestParam Long id) throws WrongInputException {
        return tutorService.update(tutorRequest, id);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) throws WrongInputException {
        tutorService.delete(id);
    }


}
