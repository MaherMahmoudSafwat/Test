package UserServices.example.UserServicesRestaurant.UsersController;

import UserServices.example.UserServicesRestaurant.Models.Users;
import UserServices.example.UserServicesRestaurant.UsersDtos.UsersDtos;
import UserServices.example.UserServicesRestaurant.UsersService.JwtService;
import UserServices.example.UserServicesRestaurant.UsersService.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/Login")
    public String LoginToA_NewUser(@RequestBody UsersDtos usersDtos)
    {
        Authentication authenticated = authenticationManager.authenticate
                (
                        new UsernamePasswordAuthenticationToken(usersDtos.getUsername(),usersDtos.getPassword())
                );
        if(authenticated.isAuthenticated())
        {
            return jwtService.generateToken(usersDtos.getUsername());
        }
        return "Invalid username or password";
    }

    @GetMapping("/Test")
    public String TestNewServiceMethod()
    {
        return "The current time is " + System.currentTimeMillis();
    }

}
