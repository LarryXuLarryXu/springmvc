package main.java.com.jetsly.webapp.web;



import main.java.com.jetsly.webapp.domain.User;
import main.java.com.jetsly.webapp.service.UserService;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserService userService;

    @RequestMapping(method=RequestMethod.GET)
    List<User> GetAll() {
        return this.userService.GetAll();
    }
}
