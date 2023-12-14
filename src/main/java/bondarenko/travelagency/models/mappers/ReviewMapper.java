package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.Amenity;
import bondarenko.travelagency.models.Review;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        Review review = new Review();
        review.setIdReview(rs.getInt("idRewiev"));
        review.setLogin(rs.getString("userLogin"));
        review.setIdRoute(rs.getInt("idRoute"));
        review.setRank(rs.getInt("rank"));
        review.setFirstName(rs.getString("firstName"));
        review.setSecondName(rs.getString("secondName"));
        review.setDescription(rs.getString("description"));
        return review;
    }
}
