package UserServices.example.UserServicesRestaurant.UsersService;

import UserServices.example.UserServicesRestaurant.Models.Users;
import UserServices.example.UserServicesRestaurant.Models.UsersPrincipal;
import UserServices.example.UserServicesRestaurant.UsersRepository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	Users users= usersRepository.findByUsername(username);
	
	if (users==null) {
		System.out.println("User 404");
		throw new UsernameNotFoundException("User 404");
	}
		 return new UsersPrincipal(users);
	}

}
