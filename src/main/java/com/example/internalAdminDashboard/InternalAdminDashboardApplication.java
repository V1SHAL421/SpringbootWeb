package com.example.internalAdminDashboard;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.Arrays;

@SpringBootApplication
/*
This annotation includes:
Configuration - tags class as source of bean definitions for app context
Enables auto config by adding beans based on classpath settings
ComponentScan - look for other components, configs and services in com/example package
*/
public class InternalAdminDashboardApplication {
	/* Entry point for Java app */
	public static void main(String[] args) {
		// When Spring app starts after ApplicationContext is fully loaded, Spring finds beans of type CommandLineRunner and calls their run methods
		SpringApplication.run(InternalAdminDashboardApplication.class, args);

	}
//	// To check application health: $ curl http://localhost:8080/api/actuator/health
//	@Bean // Tells Spring that the method will return an object that should be registered as a bean
//	// Beans are objects that are managed by Spring
//
//	/* Interface that indicates a bean should run when the Spring app context is fully started */
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		// ApplicationContext is the Spring IoC (Inversion of Control) container that holds all the beans
//		// Retrieves all beans created by app
//		/* This lambda expression implements the run method (from CommandLineRunner interface) taking in array of strings 'args' */
//		return args -> {
//			// Returns a lambda function which implements CommandLineRunner
//			System.out.println("Inspect the beans from Spring Boot");
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String name: beanNames) {
//				System.out.println(name);
//			}
//		};
//	}

}
