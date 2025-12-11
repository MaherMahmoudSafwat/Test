package Api_Gateway_Restaurant.example.Api_Gateway_Restaurant.Api_Gateway_Restaurant.example.Api_Gateway_Restaurant.Api_Gateway_Restaurant_Authentications_Filter;

import Api_Gateway_Restaurant.example.Api_Gateway_Restaurant.JwtService.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtService jwtService;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                List<String> authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
                if (authHeaders == null || authHeaders.isEmpty()) {
                    throw new RuntimeException("Missing authorization header");
                }

                String authHeader = authHeaders.get(0);

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                try
                {
                    if (!jwtService.validateToken(authHeader)) {
                        throw new RuntimeException("Invalid token");
                    }

                } catch (Exception e) {
                    System.out.println("Invalid access: " + e.getMessage());
                    throw new RuntimeException("Unauthorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config
    {}
}

