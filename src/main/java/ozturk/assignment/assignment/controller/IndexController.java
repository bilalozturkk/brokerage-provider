package ozturk.assignment.assignment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/index")
public class IndexController {
    @GetMapping("/")
    public ResponseEntity<String> index(){
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
}
