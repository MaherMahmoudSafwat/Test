package UserServices.example.UserServicesRestaurant.UsersRepository;

import UserServices.example.UserServicesRestaurant.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository  extends JpaRepository<Users,Integer> {
    public Users findByUsername(String username);
}
