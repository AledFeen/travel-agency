package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.StartPlace;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StartPlaceMapper implements RowMapper<StartPlace> {
    @Override
    public StartPlace mapRow(ResultSet rs, int rowNum) throws SQLException {
        StartPlace startPlace = new StartPlace();
        startPlace.setIdStartPlace(rs.getInt("idStartPlace"));
        startPlace.setCity(rs.getString("city"));
        startPlace.setType(rs.getString("type"));
        return startPlace;
    }
}
