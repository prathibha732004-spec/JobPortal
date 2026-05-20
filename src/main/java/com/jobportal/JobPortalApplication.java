package com.jobportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobPortalApplication {

    public static void main(String[] args) {
<<<<<<< HEAD
        org.springframework.context.ApplicationContext context = SpringApplication.run(JobPortalApplication.class, args);
        String port = context.getEnvironment().getProperty("server.port", "8080");
        System.out.println("\n=================================================");
        System.out.println("APPLICATION STARTED SUCCESSFULLY!");
        System.out.println("Link: http://localhost:" + port);
        System.out.println("=================================================\n");
=======
        SpringApplication.run(JobPortalApplication.class, args);
>>>>>>> b55a3703b788f85d649ea9ed2690019c753241ea
    }
}
