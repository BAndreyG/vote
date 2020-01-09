package ru.javawebinar.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.Query;
import ru.javawebinar.vote.controller.RestotanController;
import ru.javawebinar.vote.controller.UserController;
import ru.javawebinar.vote.model.Role;
import ru.javawebinar.vote.model.User;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "ru.javawebinar.vote")
public class VoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteApplication.class, args);
	}

}
