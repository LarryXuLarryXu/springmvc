package main.java.com.jetsly.webapp.web;

import main.java.com.jetsly.webapp.domain.User;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.AuthenticationException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value="account")
public class AccountController {


    @RequestMapping(value="/auth", method=RequestMethod.POST)
    @ResponseBody
    Object login(@RequestBody User user) {
        if (user.getUserName() == null) {
            throw new UnknownAccountException();
        }
        return Jwts.builder().setSubject(user.getUserName())
                //.claim("roles", userDb.get(login.name)).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
    }

    @RequestMapping(value="/logout", method=RequestMethod.POST)
    @ResponseBody
    Object logout(@RequestBody User user) {
        return user;
    }
}
