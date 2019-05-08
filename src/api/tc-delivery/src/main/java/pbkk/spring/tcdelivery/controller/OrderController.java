package pbkk.spring.tcdelivery.controller;

import pbkk.spring.tcdelivery.model.Order;
import pbkk.spring.tcdelivery.exception.ResourceNotFoundException;
import pbkk.spring.tcdelivery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@ComponentScan("pbkk.spring")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    // Get All Orders
    @GetMapping("")
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }
    // Create a new order
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderRepository.save(order);
    }

    // Get a Single order
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable(value = "id") Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
    }

    // Update a order
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable(value = "id") Long orderId,
                                            @Valid @RequestBody Order orderDetails) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        order.setStatus(orderDetails.getStatus());
        
        Order updatedOrder = orderRepository.save(order);
        return updatedOrder;
    }
    
    //Update status
    @PatchMapping("/{id}")
    public Order updateOrderStatus(@PathVariable(name = "id") Long orderId, @RequestParam(name = "status")
    									Boolean status) 
    {
    	Order order = orderRepository.findById(orderId)
    			.orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
    	
    	order.setStatus(status);
    	return orderRepository.save(order);
    }

    // Delete a order
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", orderId));

        orderRepository.delete(order);

        return ResponseEntity.ok().build();
    }
}