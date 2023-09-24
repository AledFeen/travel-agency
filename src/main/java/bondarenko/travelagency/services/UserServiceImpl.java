package bondarenko.travelagency.services;

import bondarenko.travelagency.models.User;
import bondarenko.travelagency.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void save(User regUser) {
        User user = new User();
        user.setUsername(regUser.getUsername());
        user.setEnabled(true);
        user.setPassword(new BCryptPasswordEncoder().encode(regUser.getPassword()));
        user.setRole("ROLE_USER");
        userRepo.save(user);
    }
}
