package main.java.com.jetsly.webapp.persistence;

import main.java.com.jetsly.webapp.domain.User;

import java.util.List;

public interface UserMapper {
	List<User> GetAll();
	User findByUserName(String userName);
}
