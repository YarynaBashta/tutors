package kunanets.tutors.controller;


import kunanets.tutors.dto.request.OrderRequest;
import kunanets.tutors.dto.response.OrderResponse;
import kunanets.tutors.dto.response.SubjectResponse;
import kunanets.tutors.exeption.WrongInputException;
import kunanets.tutors.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderResponse> findAll(){
        return orderService.findAll();
    }

    @PostMapping
    public OrderResponse save(@RequestBody @Valid OrderRequest orderRequest)throws WrongInputException {
        return orderService.save(orderRequest);
    }

    @PutMapping
    public OrderResponse update(@RequestBody @Valid OrderRequest orderRequest, @RequestParam Long id) throws WrongInputException {
        return orderService.update(orderRequest, id);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) throws WrongInputException {
        orderService.delete(id);
    }
}
