package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.User;

public interface UserRepository {
    void save(User regUser);
}
