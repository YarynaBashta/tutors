package kunanets.tutors.controller;
import kunanets.tutors.dto.request.LevelRequest;
import kunanets.tutors.dto.request.StudentRequest;
import kunanets.tutors.dto.response.LevelResponse;
import kunanets.tutors.dto.response.StudentResponse;
import kunanets.tutors.exeption.WrongInputException;
import kunanets.tutors.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/level")
public class LevelController {

    @Autowired
    private LevelService levelService;


    @GetMapping
    public List<LevelResponse> findAll(){
        return levelService.findAll();
    }

    @GetMapping("/one")
    public LevelResponse findOne(@RequestParam Long id) throws WrongInputException {
        return levelService.findOneById(id);
    }

    @PostMapping
    public LevelResponse save(@RequestBody @Valid LevelRequest levelRequest)throws WrongInputException {
        return levelService.save(levelRequest);
    }

    @PutMapping
    public LevelResponse update(@RequestBody @Valid LevelRequest levelRequest, @RequestParam Long id) throws WrongInputException {
        return levelService.update(levelRequest, id);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) throws WrongInputException {
        levelService.delete(id);
    }
}
