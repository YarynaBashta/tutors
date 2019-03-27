package kunanets.tutors.service;


import kunanets.tutors.dto.request.ResponsesRequest;
import kunanets.tutors.dto.response.ResponsesResponse;
import kunanets.tutors.entity.Responses;
import kunanets.tutors.exeption.WrongInputException;
import kunanets.tutors.repository.ResponsesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponsesService {

    @Autowired
     private  ResponsesRepository responsesRepository;
    @Autowired
    private StudentService studentService;

    @Autowired
    private TutorService tutorService;

    public List<ResponsesResponse> findAll() {
        return responsesRepository.findAll().stream().map(ResponsesResponse::new).collect(Collectors.toList());
    }

    public Responses findOne(Long id) throws WrongInputException {
        return responsesRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Responses with id " + id + " not exists"));
    }

    private Responses responsesRequestToResponses( ResponsesRequest request, Responses responses) throws WrongInputException{
        if (responses == null){
            responses = new Responses();
        }
        responses.setText(request.getText());
        responses.setStudent(studentService.findOne(request.getStudentId()));
        responses.setTutor(tutorService.findOne(request.getTutorId()));
        return responsesRepository.save(responses);
    }

    public ResponsesResponse save(ResponsesRequest request) throws WrongInputException {
        return new ResponsesResponse(responsesRequestToResponses(request, null));
    }

    public ResponsesResponse update(ResponsesRequest request, Long id) throws WrongInputException {
        return new ResponsesResponse(responsesRequestToResponses(request, findOne(id)));
    }

    public void delete(Long id) throws WrongInputException {
        responsesRepository.delete(findOne(id));
    }
}
