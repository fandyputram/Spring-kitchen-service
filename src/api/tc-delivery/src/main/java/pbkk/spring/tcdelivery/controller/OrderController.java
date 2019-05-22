package pbkk.spring.tcdelivery.controller;

import pbkk.spring.tcdelivery.model.*;
import pbkk.spring.tcdelivery.exception.ResourceNotFoundException;
import pbkk.spring.tcdelivery.repository.OrderRepository;
import pbkk.spring.tcdelivery.repository.StatusRepository;
import pbkk.spring.tcdelivery.aop.RestaurantTokenRequired;
import pbkk.spring.tcdelivery.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

@ComponentScan("pbkk.spring")
@RestController
@RequestMapping("")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    
    @Autowired
    StatusRepository statusRepository;
    
    // Get All Orders
    @RestaurantTokenRequired
    @GetMapping("/order")
    public List<Order> getAllOrder() {
    	return orderRepository.findAll();
    }
    
    // Create a new order
    @RestaurantTokenRequired 
    @PostMapping(value = "/order", produces = "application/json")
    public Order createOrder(@RequestParam ("order") String order) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
	    Order orderObject = mapper.readValue(order, Order.class);
	    return orderRepository.save(orderObject);
    }
/*
    // Create a new status
    @PostMapping("/status")
    public Status createStatus(@Valid @RequestBody Status status) {
        return statusRepository.save(status);
    }*/
    
    // Get a Single order
    @RestaurantTokenRequired
    @GetMapping("/order/{id}")
    public Order getOrderById(String token, 
    		@PathVariable(value = "id") Long orderId) throws Exception {
  			return orderRepository.findById(orderId)
  	                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
  	
    }
    
    // Get status from order
    @RestaurantTokenRequired
    @GetMapping("/order/{id}/status")
    public Status getStatByOrder(@PathVariable(value = "id") Long orderId) throws Exception {
    	
  		Order order = orderRepository.findById(orderId)
  	    		.orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
  	    return order.getStatus();
    }

    // Update a order
    @RestaurantTokenRequired
    @PutMapping(value = "/order/{id}", produces = "application/json")
    public Order updateOrder(@PathVariable(value = "id") Long orderId,
    									@RequestParam ("status") String status) throws Exception {

  		  Order order = orderRepository.findById(orderId)
                  .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
          ObjectMapper mapper = new ObjectMapper();
      	  Status statusObject = mapper.readValue(status, Status.class);
          order.setStatus(statusObject);
	          return orderRepository.save(order);
    }
    
    // Get All Status
    @RestaurantTokenRequired
    @GetMapping("/status")
    public List<Status> getAllStatus() throws Exception {
        return statusRepository.findAll();
    }
    
    // Get a Single Status
    @RestaurantTokenRequired
    @GetMapping("/status/{id}")
    public Status getStatusById(@PathVariable(value = "id") Long statusId) throws Exception {
    	return statusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("Status", "id", statusId)); }
    
    // Delete a order
    /*@DeleteMapping("/order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", orderId));

        orderRepository.delete(order);

        return ResponseEntity.ok().build();
    }*/

}