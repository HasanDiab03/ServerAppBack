package com.manageservers.ManageServers;

import com.manageservers.ManageServers.enumeration.Status;
import com.manageservers.ManageServers.model.Server;
import com.manageservers.ManageServers.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ManageServersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageServersApplication.class, args);
	}


	// every piece of code written here will run right after we run the program
//	@Bean // annotation so that spring recognizes it
//	CommandLineRunner run(ServerRepo serverRepo) {
//		return args -> {
//			serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "PC",
//					"http://localhost:8080/server/image/server1.jpeg",
//					Status.SERVER_UP));
//			serverRepo.save(new Server(null, "192.168.1.58", "Fedora Linux", "16 GB", "Dell Tower",
//					"http://localhost:8080/server/image/server2.jpeg",
//					Status.SERVER_DOWN));
//			serverRepo.save(new Server(null, "192.168.1.21", "MS 2008", "32 GB", "Web Server",
//							"http://localhost:8080/server/image/server3.jpeg",
//					Status.SERVER_UP));
//			serverRepo.save(new Server(null, "192.168.1.14", "Red Hat Enterprise Linux", "64 GB", "Mail Server",
//							"http://localhost:8080/server/image/server4.jpeg",
//					Status.SERVER_DOWN));
//		};
//	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) { 	
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
			}
		};
	}

}
