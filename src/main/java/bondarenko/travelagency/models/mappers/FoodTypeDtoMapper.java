package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.dto.FoodTypeDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodTypeDtoMapper implements RowMapper<FoodTypeDto> {
    @Override
    public FoodTypeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        FoodTypeDto hft = new FoodTypeDto();
        hft.setId(rs.getInt("id"));
        hft.setIdHotel(rs.getInt("idHotel"));
        hft.setIdFoodType(rs.getInt("idFoodType"));
        hft.setPrice(rs.getInt("price"));
        hft.setName(rs.getString("ftName"));
        hft.setDescription(rs.getString("ftDescription"));
        return hft;
    }
}
