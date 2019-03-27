package kunanets.tutors.service;

import kunanets.tutors.dto.request.OrderRequest;
import kunanets.tutors.dto.response.OrderResponse;
import kunanets.tutors.entity.Order;
import kunanets.tutors.exeption.WrongInputException;
import kunanets.tutors.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TutorService tutorService;

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }
    public Order findOne(Long id) throws WrongInputException {
        return orderRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Order with id " + id + " not exists"));
    }
    private Order orderRequestToOrder( OrderRequest request, Order order) throws WrongInputException{
        if (order == null){
            order = new Order();
        }
        order.setDate(request.getDate());
        order.setStudent(studentService.findOne(request.getStudentId()));
        order.setTutor(tutorService.findOne(request.getTutorId()));
        return orderRepository.save(order);
    }
    public OrderResponse save(OrderRequest request) throws WrongInputException {
        return new OrderResponse(orderRequestToOrder(request, null));
    }

    public OrderResponse update(OrderRequest request, Long id) throws WrongInputException {
        return new OrderResponse(orderRequestToOrder(request, findOne(id)));
    }


    public void delete(Long id) throws WrongInputException {
        orderRepository.delete(findOne(id));
    }
}
