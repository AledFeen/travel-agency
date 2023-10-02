package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.Hotel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelMapper implements RowMapper<Hotel> {
    @Override
    public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getInt("idHotel"));
        hotel.setHotelName(rs.getString("hotelName"));
        hotel.setRank(rs.getInt("rank"));
        hotel.setCity(rs.getString("city"));
        hotel.setLocation(rs.getString("location"));
        hotel.setIdGroup(rs.getInt("idGroup"));
        return hotel;
    }
}
