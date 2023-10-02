package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.Facility;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacilityMapper implements RowMapper<Facility> {

    @Override
    public Facility mapRow(ResultSet rs, int rowNum) throws SQLException {
        Facility facility = new Facility();
        facility.setIdFacility(rs.getInt("idFacility"));
        facility.setFacilityName(rs.getString("facilityName"));
        facility.setFacilityDescription(rs.getString("facilityDescription"));
        facility.setIdHotel(rs.getInt("idHotel"));
        return facility;
    }
}
