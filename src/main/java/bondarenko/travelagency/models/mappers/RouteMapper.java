package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.Route;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteMapper implements RowMapper<Route> {
    @Override
    public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
        Route route = new Route();
        route.setIdRoute(rs.getInt("idRoute"));
        route.setRName(rs.getString("rName"));
        route.setDescription(rs.getString("description"));
        route.setPeopleCount(rs.getInt("peopleCount"));
        route.setStartData(rs.getDate("startData"));
        route.setEndData(rs.getDate("endData"));
        route.setMinPrice(rs.getInt("minPriceRoute"));
        route.setMaxPrice(rs.getInt("maxPriceRoute"));
        route.setTransportPrice(rs.getInt("transportPrice"));
        return route;
    }
}
