package Api_Gateway_Restaurant.example.Api_Gateway_Restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayRestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayRestaurantApplication.class, args);
	}

}
