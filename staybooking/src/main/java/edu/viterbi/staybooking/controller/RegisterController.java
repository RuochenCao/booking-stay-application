package edu.viterbi.staybooking.controller;

import org.springframework.web.bind.annotation.RestController;
import edu.viterbi.staybooking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import edu.viterbi.staybooking.model.User;
import edu.viterbi.staybooking.model.UserRole;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class RegisterController {
    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    //@PostMapping(“/register/guest”) annotation to indicate the API supports POST method and maps to the /register/guest path.
    //@RequestBody annotation to help convert the request body from JSON format to a Java object.
    @PostMapping("/register/guest")
    public void addGuest(@RequestBody User user) {
        registerService.add(user, UserRole.ROLE_GUEST);
    }

    @PostMapping("/register/host")
    public void addHost(@RequestBody User user) {
        registerService.add(user, UserRole.ROLE_HOST);
    }


}
