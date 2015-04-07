package main.java.com.jetsly.webapp.web;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/*
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.authc.UsernamePasswordToken;
*/

@RestController
public class HelloController {

    @RequestMapping("/")
    String home() {
        return "Hello World";
    }

    /*
    @RequestMapping("/test")
    Object test() {
       final Subject currentUser  = SecurityUtils.getSubject();
       UsernamePasswordToken token =new UsernamePasswordToken("admin", "admin");
       currentUser.login(token);
       return currentUser.getPrincipal();
    }
    */
}
