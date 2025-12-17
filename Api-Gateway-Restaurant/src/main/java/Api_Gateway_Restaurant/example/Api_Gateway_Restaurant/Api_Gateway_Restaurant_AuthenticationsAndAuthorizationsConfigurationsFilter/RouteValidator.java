package Api_Gateway_Restaurant.example.Api_Gateway_Restaurant.Api_Gateway_Restaurant_AuthenticationsAndAuthorizationsConfigurationsFilter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator
{
    public static final List<String> openApiEndpoints = List.of(
            "/Restaurant/Users/Register",
            "/Restaurant/Users/Login",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
