package Eureka_Restaurant_Registry_Service.example.Eureka_Restaurant_Registry_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaRestaurantRegistryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaRestaurantRegistryServiceApplication.class, args);
	}

}
