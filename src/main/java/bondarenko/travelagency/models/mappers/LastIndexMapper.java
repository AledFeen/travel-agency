package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.LastNum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LastIndexMapper implements RowMapper<LastNum> {
    @Override
    public LastNum mapRow(ResultSet rs, int rowNum) throws SQLException {
        LastNum last_num = new LastNum();
        last_num.setNum(rs.getInt("last_num"));
        return last_num;
    }
}
