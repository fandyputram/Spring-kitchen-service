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
@RequestMapping("")
public class HomeController {
	 @Autowired
	 StatusRepository statusRepository;
	 MenuDetailsRepository menuDetRepository;
	 MenuRepository menuRepository;
	 UserRepository userRepository;
	    
    // Create a new menu
    @PostMapping("/menu")
    @ResponseStatus(HttpStatus.CREATED)
    public Menu createMenu(@Valid @RequestBody Menu menu) {
        return menuRepository.save(menu);
    }
    
    // Create a new menu details
    @PostMapping("/menu_details")
    @ResponseStatus(HttpStatus.CREATED)
    public MenuDetails createOrder(@Valid @RequestBody MenuDetails menuDetails) {
        return menuDetRepository.save(menuDetails);
    }
    // Create a new user
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
    // Create a new status
    @PostMapping("/status")
    @ResponseStatus(HttpStatus.CREATED)
    public Status createStatus(@Valid @RequestBody Status status) {
        return statusRepository.save(status);
    }
}