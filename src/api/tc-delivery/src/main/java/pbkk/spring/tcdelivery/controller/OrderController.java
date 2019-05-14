package pbkk.spring.tcdelivery.controller;

import pbkk.spring.tcdelivery.model.*;
import pbkk.spring.tcdelivery.exception.ResourceNotFoundException;
import pbkk.spring.tcdelivery.repository.*;
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
    StatusRepository statusRepository;
    UserRepository userRepository;

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
    
    // Get user from a single order
    @GetMapping("/{id}/user")
    public User getUserByOrder(@PathVariable(value = "id") Long orderId) {
    	Order userOrder = orderRepository.findById(orderId)
    			.orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
    	User user = userOrder.getUser();
    	return user;
    }
    
    // Get status from a single order
    @GetMapping("/{id}/status")
    public Status getStatusByOrder(@PathVariable(value = "id") Long orderId) {
    	Order statOrder = orderRepository.findById(orderId)
    			.orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
    	Status status = statOrder.getStatus();
    	return status;
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
    public Order updateOrderStatus(@PathVariable(name = "id") Long orderId, @PathVariable(name = "status")
    									Long statId) 
    {
    	Order order = orderRepository.findById(orderId)
    			.orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
    	
    	Status status = statusRepository.findById(statId)
    			.orElseThrow(() -> new ResourceNotFoundException("Status", "status", statId));
    	
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