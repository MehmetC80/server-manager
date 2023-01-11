package de.memozone.servermanager;

import de.memozone.servermanager.enumeration.Status;
import de.memozone.servermanager.model.Server;
import de.memozone.servermanager.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }


}
