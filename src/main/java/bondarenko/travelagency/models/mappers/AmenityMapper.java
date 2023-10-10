package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.Amenity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AmenityMapper implements RowMapper<Amenity> {
    @Override
    public Amenity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Amenity amenity = new Amenity();
        amenity.setIdAmenity(rs.getInt("idAmenity"));
        amenity.setName(rs.getString("amName"));
        return amenity;
    }
}
