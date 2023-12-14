package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.Review;

import bondarenko.travelagency.models.mappers.FoodTypeDtoMapper;
import bondarenko.travelagency.models.mappers.ReviewMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
public class ReviewRepository {
    private final NamedParameterJdbcTemplate jdbc;
    public List<Review> getReviewsByRouteId(int idRoute) {
        String sql = "select r.idRewiev, r.idRoute, r.userLogin, r.rank, r.description, u.firstName, u.secondName from rewiev r inner join `user` u on r.userLogin = u.username where r.idRoute = :id;";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", idRoute);
        return jdbc.query(sql, source, new ReviewMapper());
    }
    @Transactional
    public void addReview(int idRoute, String userLogin, int rank, String description) {
        String sql = "INSERT INTO rewiev(idRoute, userLogin, `rank`, description) VALUES (:idRoute, :login, :rank, :desc)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("idRoute", idRoute);
        source.addValue("login", userLogin);
        source.addValue("rank", rank);
        source.addValue("desc", description);
        jdbc.update(sql, source);
    }

    @Transactional
    public void deleteReview(int id) {
        String sql = "DELETE FROM rewiev where idRewiev = :id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        jdbc.update(sql, source);
    }
}
