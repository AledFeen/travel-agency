package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.HotelFoodType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelFoodTypeMapper implements RowMapper<HotelFoodType> {
    @Override
    public HotelFoodType mapRow(ResultSet rs, int rowNum) throws SQLException {
        HotelFoodType hft = new HotelFoodType();
        hft.setId(rs.getInt("id"));
        hft.setIdHotel(rs.getInt("idHotel"));
        hft.setIdFoodType(rs.getInt("idFoodType"));
        hft.setPrice(rs.getInt("price"));
        return hft;
    }
}
