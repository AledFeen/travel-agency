package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.dto.DealDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DealDtoMapper implements RowMapper<DealDto> {
    @Override
    public DealDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        DealDto deal = new DealDto();
        deal.setIdDeal(rs.getInt("idDeal"));
        deal.setIdRoute(rs.getInt("idRoute"));
        deal.setRouteName(rs.getString("rName"));
        deal.setUserLogin(rs.getString("userLogin"));
        deal.setTotalPrice(rs.getInt("totalPrice"));
        deal.setIdStatus(rs.getInt("idStatus"));
        deal.setStatusName(rs.getString("statusName"));
        deal.setIdStartPlace(rs.getInt("idStartPlace"));
        deal.setCity(rs.getString("city"));
        deal.setType(rs.getString("type"));
        deal.setPhoneNumber(rs.getInt("phoneNumber"));
        deal.setEmail(rs.getString("email"));
        return deal;
    }
}
