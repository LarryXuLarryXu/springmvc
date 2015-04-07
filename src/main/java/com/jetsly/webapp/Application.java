package main.java.com.jetsly.webapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ApplicationContext;

import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
       SpringApplication app = new SpringApplication(Application.class);
       //defalut show Banner
       //app.setShowBanner(false);
       ApplicationContext ctx =app.run(args);
    }
}
