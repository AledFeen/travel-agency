package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements RowMapper<Country> {

    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        Country country = new Country();
        country.setIdCountry(rs.getInt("idCountry"));
        country.setCountryName(rs.getString("countryName"));
        return country;
    }
}
