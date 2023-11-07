package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.Firm;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FirmMapper implements RowMapper<Firm> {

    @Override
    public Firm mapRow(ResultSet rs, int rowNum) throws SQLException {
        Firm firm = new Firm();
        firm.setIdGroup(rs.getInt("idGroup"));
        firm.setFirmName(rs.getString("firmName"));
        firm.setIdCountry(rs.getInt("idCountry"));
        return firm;
    }
}
