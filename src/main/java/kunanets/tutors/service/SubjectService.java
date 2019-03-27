package kunanets.tutors.service;

import kunanets.tutors.dto.request.PaginationRequest;
import kunanets.tutors.dto.request.SubjectRequest;
import kunanets.tutors.dto.response.DataResponse;
import kunanets.tutors.dto.response.SubjectResponse;
import kunanets.tutors.entity.Level;
import kunanets.tutors.entity.Subject;
import kunanets.tutors.exeption.WrongInputException;
import kunanets.tutors.repository.SubjectRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private LevelService levelService;


    public List<SubjectResponse> findAll() {
        return subjectRepository.findAll().stream()
                .map(SubjectResponse::new)
                .collect(Collectors.toList());
    }
    public Subject findOne(Long id) throws WrongInputException {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Subject with id " + id + " not exists"));
    }
    public SubjectResponse findOneById(Long id) throws WrongInputException {
        return new SubjectResponse(findOne(id));
    }
    @SneakyThrows
    private Subject subjectRequestToSubject ( SubjectRequest request, Subject subject)throws WrongInputException {
        if (subject == null){
            subject = new Subject();
        }

        subject.setName(request.getName());
        subject = subjectRepository.save(subject);
        List<Level> levels = new ArrayList<>();
        for (Long id : request.getLevelsId()){
            levels.add(levelService.findOne(id));
        }
        subject.setLevels(levels);
        return subjectRepository.save(subject);
    }
    public SubjectResponse save(SubjectRequest request) throws WrongInputException {
        return new SubjectResponse(subjectRequestToSubject(request, null));
    }

    public SubjectResponse update(SubjectRequest request, Long id) throws WrongInputException {
        return new SubjectResponse(subjectRequestToSubject(request, findOne(id)));
    }


    public void delete(Long id) throws WrongInputException {
        subjectRepository.delete(findOne(id));
    }

}
