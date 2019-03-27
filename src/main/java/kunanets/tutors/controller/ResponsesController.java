package kunanets.tutors.controller;


import kunanets.tutors.dto.request.ResponsesRequest;
import kunanets.tutors.dto.response.LevelResponse;
import kunanets.tutors.dto.response.ResponsesResponse;
import kunanets.tutors.exeption.WrongInputException;
import kunanets.tutors.service.ResponsesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/responses")
public class ResponsesController {

    @Autowired
    private ResponsesService responsesService;

    @GetMapping
    public List<ResponsesResponse> findAll(){
        return responsesService.findAll();
    }

    @PostMapping
    public ResponsesResponse save(@RequestBody @Valid ResponsesRequest responsesRequest) throws WrongInputException{
        return responsesService.save(responsesRequest);
    }

    @PutMapping
    public ResponsesResponse update(@RequestBody @Valid ResponsesRequest responsesRequest, @RequestParam Long id) throws WrongInputException {
        return responsesService.update(responsesRequest, id);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) throws WrongInputException {
        responsesService.delete(id);
    }
}
