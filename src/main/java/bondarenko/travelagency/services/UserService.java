package bondarenko.travelagency.services;

import bondarenko.travelagency.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService {
    void save(User regUser);
}
