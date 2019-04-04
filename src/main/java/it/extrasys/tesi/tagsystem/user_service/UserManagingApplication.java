package it.extrasys.tesi.tagsystem.user_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Auto-generated Javadoc
/**
 * The Class for starting the spring boot application.
 */
@SpringBootApplication
public class UserManagingApplication implements CommandLineRunner {

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(UserManagingApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        // TODO Auto-generated method stub
    	System.getenv().forEach((f,k)-> System.out.println("Var : "+f+"val : "+k));
    }
}
