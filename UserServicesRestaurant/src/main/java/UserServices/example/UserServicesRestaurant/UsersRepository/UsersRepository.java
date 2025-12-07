package UserServices.example.UserServicesRestaurant.UsersRepository;

import UserServices.example.UserServicesRestaurant.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository  extends JpaRepository<Users,Integer> {
    public Users findByUsername(String username);
}
