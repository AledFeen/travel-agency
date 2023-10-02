package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.dto.EstablishmentDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EstablishmentDtoMapper implements RowMapper<EstablishmentDto> {
    @Override
    public EstablishmentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        EstablishmentDto establishment = new EstablishmentDto();
        establishment.setId(rs.getInt("idEstablishment"));
        establishment.setName(rs.getString("estName"));
        establishment.setDescription(rs.getString("estDescription"));
        establishment.setHotelId(rs.getInt("idHotel"));
        establishment.setType(rs.getString("estTypeName"));
        return establishment;
    }
}
