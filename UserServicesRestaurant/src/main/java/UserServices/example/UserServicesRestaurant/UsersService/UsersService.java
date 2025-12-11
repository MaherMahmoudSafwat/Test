package UserServices.example.UserServicesRestaurant.UsersService;

import UserServices.example.UserServicesRestaurant.Models.Users;
import UserServices.example.UserServicesRestaurant.Models.UsersPrincipal;
import UserServices.example.UserServicesRestaurant.UsersRepository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void saveUser(Users users)
    {
        users.setPassword(encoder.encode(users.getPassword()));
        System.out.println(users.getPassword());
        usersRepository.save(users) ;
    }

    public UsersPrincipal getUsersDetails(String username)
    {
        return new UsersPrincipal(usersRepository.findByUsername(username));
    }
}
