package ro.sorin.pynt.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hello-world")
    public String sayHello(){
        return "hello";
    }
}
