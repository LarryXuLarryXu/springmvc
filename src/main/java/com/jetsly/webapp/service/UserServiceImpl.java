package main.java.com.jetsly.webapp.service;

import main.java.com.jetsly.webapp.domain.User;
import main.java.com.jetsly.webapp.persistence.UserMapper;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service("UserService")
public class UserServiceImpl implements UserService{

   @Autowired
   private  UserMapper userMapper;

   @Override
   public List<User> GetAll(){
      return this.userMapper.GetAll();
   }
}
