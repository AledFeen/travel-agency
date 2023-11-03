package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.FoodType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodTypeMapper implements RowMapper<FoodType> {
    @Override
    public FoodType mapRow(ResultSet rs, int rowNum) throws SQLException {
        FoodType foodType = new FoodType();
        foodType.setId(rs.getInt("idFoodType"));
        foodType.setName(rs.getString("ftName"));
        foodType.setDescription(rs.getString("ftDescription"));
        return foodType;
    }
}
