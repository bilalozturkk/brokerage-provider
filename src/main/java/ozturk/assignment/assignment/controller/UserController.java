package ozturk.assignment.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ozturk.assignment.assignment.model.User;
import ozturk.assignment.assignment.payload.response.MessageResponse;
import ozturk.assignment.assignment.repository.UserRepository;
import ozturk.assignment.assignment.security.services.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;


    /**
     * Get all the users
     *
     * @return ResponseEntity
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        try {
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the user by id
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable("id") Long id) {
            //check if employee exist in database
        Optional<User> user = userRepository.findById(id);

        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
