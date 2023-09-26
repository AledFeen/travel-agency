package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final NamedParameterJdbcTemplate jdbc;

    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    @Transactional
    public void save(User regUser) {
        String sql = "INSERT INTO user(username, password, role, enabled, firstName, secondName) VALUES (:username, :pass, :role, :enabled, :firstName, :secondName)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("username", regUser.getUsername());
        parameters.addValue("pass", regUser.getPassword());
        parameters.addValue("role", regUser.getRole());
        int enabled = 0;
        if(regUser.isEnabled()) { enabled = 1; }
        parameters.addValue("enabled", enabled);
        parameters.addValue("firstName", regUser.getFirstName());
        parameters.addValue("secondName", regUser.getSecondName());
        jdbc.update(sql, parameters);
    }
}
