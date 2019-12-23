package ru.javawebinar.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.vote.controller.RestotanController;
import ru.javawebinar.vote.controller.UserController;
import ru.javawebinar.vote.model.Role;
import ru.javawebinar.vote.model.User;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "ru.javawebinar.vote")
public class VoteApplication {

	public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            UserController userController = appCtx.getBean(UserController.class);
            System.out.println(userController.getAll());
            //userController.createOrUpdate(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));

            RestotanController  controller=appCtx.getBean(RestotanController.class);
            System.out.println(controller.getAll());
        }
		SpringApplication.run(VoteApplication.class, args);
	}

}
