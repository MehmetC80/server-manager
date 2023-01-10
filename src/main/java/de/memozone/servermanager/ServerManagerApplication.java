package de.memozone.servermanager;

import de.memozone.servermanager.enumeration.Status;
import de.memozone.servermanager.model.Server;
import de.memozone.servermanager.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerManagerApplication.class, args);
	}

	/*
	@Bean
	CommandLineRunner run(ServerRepository serverRepository){
		return args -> {
			serverRepository.save(new Server(null,"192.168.1.112","Ubuntu Linux","16 GB","Personal PC","http://localhost:8084/server/image/server01.png", Status.SERVER_UP));
			serverRepository.save(new Server(null,"192.168.1.100","Karli Linux","64 GB","Dell Tower","http://localhost:8084/server/image/server02.png", Status.SERVER_DOWN));
			serverRepository.save(new Server(null,"192.168.1.11","Fedora Linux","8 GB","Web Server","http://localhost:8084/server/image/server03.png", Status.SERVER_DOWN));
			serverRepository.save(new Server(null,"192.168.1.154","Arch Linux","32 GB","Work PC","http://localhost:8084/server/image/server04.png", Status.SERVER_UP));
			serverRepository.save(new Server(null,"192.168.1.146","Debian Linux","16 GB","Server","http://localhost:8084/server/image/server05.png", Status.SERVER_UP));
		};
			}
	 */




}
