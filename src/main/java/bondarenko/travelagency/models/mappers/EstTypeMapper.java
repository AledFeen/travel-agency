package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.EstType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EstTypeMapper implements RowMapper<EstType> {
    @Override
    public EstType mapRow(ResultSet rs, int rowNum) throws SQLException {
        EstType type = new EstType();
        type.setIdEstType(rs.getInt("idEstablishType"));
        type.setEstTypeName(rs.getString("estTypeName"));
        return type;
    }
}
