package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository {
    void save(User regUser);
}
