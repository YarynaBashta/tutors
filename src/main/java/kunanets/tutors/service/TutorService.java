package kunanets.tutors.service;

import kunanets.tutors.dto.request.PaginationRequest;
import kunanets.tutors.dto.request.TutorFilterRequest;
import kunanets.tutors.dto.request.TutorRequest;
import kunanets.tutors.dto.response.DataResponse;
import kunanets.tutors.dto.response.SubjectResponse;
import kunanets.tutors.dto.response.TutorResponse;
import kunanets.tutors.entity.Subject;
import kunanets.tutors.entity.Tutor;
import kunanets.tutors.exeption.WrongInputException;
import kunanets.tutors.repository.TutorRepository;
import kunanets.tutors.specification.TutorSpecification;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private SubjectService subjectService;

    public List<TutorResponse> findAll() {
        return tutorRepository.findAll().stream()
                .map(TutorResponse::new)
                .collect(Collectors.toList());
    }

    public Tutor findOne(Long id) throws WrongInputException {
        return tutorRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Tutor with id " + id + " not exists"));
    }

    @SneakyThrows
    private Tutor tutorRequestToTutor(TutorRequest request, Tutor tutor )throws WrongInputException {
        if (tutor == null) {
            tutor = new Tutor();
        }
        tutor.setName(request.getName());
        tutor.setEmail(request.getEmail());
        tutor.setPrice(request.getPrice());
        tutor.setPhoto(request.getPhoto());
        tutor = tutorRepository.save(tutor);

        List<Subject> subjects = new ArrayList<>();
//        request.getSubjectsId().forEach(id -> subjects.add(subjectService.findOne(id)));
        for (Long id :  request.getSubjectsId()){
            subjects.add(subjectService.findOne(id));
        }
        tutor.setSubjects(subjects);
        return tutorRepository.save(tutor);
    }
    public TutorResponse save(TutorRequest request) throws WrongInputException {
        return new TutorResponse(tutorRequestToTutor(request, null));
    }

    public TutorResponse update(TutorRequest request, Long id) throws WrongInputException {
        return new TutorResponse(tutorRequestToTutor(request, findOne(id)));
    }

    public void delete(Long id) throws WrongInputException {
        tutorRepository.delete(findOne(id));
    }


    public DataResponse <TutorResponse> findAll (PaginationRequest paginationRequest) {
        Page<Tutor> page = tutorRepository.findAll(paginationRequest.mapToPageRequest());
        return new DataResponse<>(
                page.get().map(TutorResponse::new).collect(Collectors.toList()),
                page.getTotalPages(),
                page.getTotalElements());
    }
    public DataResponse<TutorResponse> filterByFilter(TutorFilterRequest filterRequest){
        Page<Tutor> page = tutorRepository.findAll(new TutorSpecification(filterRequest),
                filterRequest.getPagination().mapToPageRequest());
        return new DataResponse<>(page.get().map(TutorResponse::new).collect(Collectors.toList()), page.getTotalPages(), page.getTotalElements());
    }






}
