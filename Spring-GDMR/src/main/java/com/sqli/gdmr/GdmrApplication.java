package com.sqli.gdmr;

import com.sqli.gdmr.Enums.Role;
import com.sqli.gdmr.Models.Admin;
import com.sqli.gdmr.Models.User;
import com.sqli.gdmr.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class GdmrApplication {


	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(GdmrApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {
			if(userRepository.findByUsername("admin").isEmpty()){
				Admin admin = new Admin();
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setNom("Admin");
				admin.setPrenom("User");
				admin.setRole(Role.ADMIN);
				admin.setDateNaissance(new Date());
				userRepository.save(admin);
				System.out.println("Admin user created");
			} else {
				System.out.println("Admin user already exists");
			}
		};
	}
}
