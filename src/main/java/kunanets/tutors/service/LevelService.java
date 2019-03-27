package kunanets.tutors.service;

import kunanets.tutors.dto.request.LevelRequest;
import kunanets.tutors.dto.response.LevelResponse;
import kunanets.tutors.entity.Level;
import kunanets.tutors.exeption.WrongInputException;
import kunanets.tutors.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LevelService {

    @Autowired
    private LevelRepository levelRepository;


    public List<LevelResponse> findAll() {
        return levelRepository.findAll().stream()
                .map(LevelResponse::new)
                .collect(Collectors.toList());
    }
    public Level findOne(Long id) throws WrongInputException {
        return levelRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Level with id " + id + " not exists"));
    }
    public LevelResponse findOneById(Long id) throws WrongInputException {
        return new LevelResponse(findOne(id));
    }
    private Level levelRequestToLevel( LevelRequest request, Level level) throws WrongInputException{
        if (level == null){
            level = new Level();
        }
        level.setName(request.getName());

        return levelRepository.save(level);
    }
    public LevelResponse save(LevelRequest request) throws WrongInputException {
        return new LevelResponse(levelRequestToLevel(request, null));
    }

    public LevelResponse update(LevelRequest request, Long id) throws WrongInputException {
        return new LevelResponse(levelRequestToLevel(request, findOne(id)));
    }

    public void delete(Long id) throws WrongInputException {
        levelRepository.delete(findOne(id));
    }
}
