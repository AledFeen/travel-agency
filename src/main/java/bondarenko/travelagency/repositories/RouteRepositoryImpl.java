package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.Route;
import bondarenko.travelagency.models.mappers.RouteMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@AllArgsConstructor
public class RouteRepositoryImpl implements RouteRepository {
    private final NamedParameterJdbcTemplate jdbc;
    @Override
    @Transactional
    public void addRoute(Route route) {
        String sql = "INSERT INTO route( rName, description, peopleCount, startData, endData, minPriceRoute, maxPriceRoute, transportPrice, publish)" +
                "VALUES ( :name, :description, :count, :start, :end, :minPrice, :maxPrice, :trPrice, :publ)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("name", route.getRName());
        source.addValue("description", route.getDescription());
        source.addValue("count", route.getPeopleCount());
        source.addValue("start", route.getStartData());
        source.addValue("end", route.getEndData());
        source.addValue("minPrice", 0);
        source.addValue("maxPrice", 0);
        source.addValue("trPrice", 0);
        source.addValue("publ", 0);
        jdbc.update(sql, source);
    }

    @Override
    public List<Route> getRouteList() {
        String sql = "Select * from route";
        return jdbc.query(sql, new RouteMapper());
    }

    @Override
    public Route getRouteById(int id) {
        String sql = " Select * from route where idRoute = :id LIMIT 1";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        return jdbc.queryForObject(sql, source, new RouteMapper());
    }

    @Override
    @Transactional
    public void updateRoute(Route route) {
        String sql = "UPDATE route SET rName = :name, description = :description, peopleCount = :count, startData = :start," +
                " endData =:end, minPriceRoute = :minPrice, maxPriceRoute = :maxPrice, transportPrice = :trPrice, publish = :publ WHERE idRoute = :id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", route.getIdRoute());
        source.addValue("name", route.getRName());
        source.addValue("description", route.getDescription());
        source.addValue("count", route.getPeopleCount());
        source.addValue("start", route.getStartData());
        source.addValue("end", route.getEndData());
        source.addValue("minPrice", route.getMinPrice());
        source.addValue("maxPrice", route.getMaxPrice());
        source.addValue("trPrice", route.getTransportPrice());
        source.addValue("publ", route.getIsPublish());
        jdbc.update(sql, source);
    }
}
