package MenusServices.example.MenusServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MenusServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenusServicesApplication.class, args);
	}

}
