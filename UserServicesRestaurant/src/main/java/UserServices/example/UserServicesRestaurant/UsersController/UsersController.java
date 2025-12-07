package UserServices.example.UserServicesRestaurant.UsersController;

import UserServices.example.UserServicesRestaurant.Models.Users;
import UserServices.example.UserServicesRestaurant.UsersDtos.UsersDtos;
import UserServices.example.UserServicesRestaurant.UsersService.JwtService;
import UserServices.example.UserServicesRestaurant.UsersService.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Restaurant")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/Register")
    public String addNewUser(@RequestBody Users users)
    {
        usersService.saveUser(users);
        return "Users are saved successfully";
    }

    @PostMapping("/Generate")
    public String getToken(@RequestBody UsersDtos usersDtos) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usersDtos.getUsername(), usersDtos.getPassword()));
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(usersDtos.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/Validate")
    public String validateToken(@RequestParam("Token") String Token) {
        UserDetails usersDetails = (UserDetails) usersService.getUsersDetails(jwtService.extractUserName(Token));
        jwtService.validateToken(Token,usersDetails);
        return "Token is valid";
    }
}
