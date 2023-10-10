package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.Amenity;
import bondarenko.travelagency.models.mappers.AmenityMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@AllArgsConstructor
@Repository
public class AmenityRepositoryImpl implements AmenityRepository {
    private final NamedParameterJdbcTemplate jdbc;
    @Override
    public List<Amenity> getAmenities() {
        String sql = "select amenity.idAmenity, amenity.amName from amenity";
        MapSqlParameterSource source = new MapSqlParameterSource();
        return jdbc.query(sql, source, new AmenityMapper());
    }

    @Override
    public List<Amenity> getRoomAmenities(int idRoom) {
        String sql = "select amenity.idAmenity, amenity.amName from amenity inner join room_amenity " +
                "on amenity.idAmenity = room_amenity.idAmenity" +
                " where room_amenity.idRoom = :idRoom";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("idRoom", idRoom);
        return jdbc.query(sql, source, new AmenityMapper());
    }

    @Override
    @Transactional
    public void addAmenity(Amenity amenity) {
        String sql = "INSERT INTO amenity(amName) VALUES (:name)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("name", amenity.getName());
        jdbc.update(sql, source);
    }

    @Override
    @Transactional
    public void deleteAmenity(int idAmenity) {
        String sql = "delete from amenity where idAmenity = :id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", idAmenity);
        jdbc.update(sql, source);
    }

    @Override
    @Transactional
    public void addAmenityForRoom(int idAmenity, int idRoom) {
        String sql = "INSERT INTO room_amenity(idRoom, idAmenity) VALUES (:idRoom, :idAmenity)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("idAmenity", idAmenity);
        source.addValue("idRoom", idRoom);
        jdbc.update(sql, source);
    }

    @Override
    @Transactional
    public void deleteAmenityFromRoom(int idAmenity, int idRoom) {
        String sql = "delete from room_amenity where idAmenity = :idAm and idRoom = :idRoom";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("idAm", idAmenity);
        source.addValue("idRoom", idRoom);
        jdbc.update(sql, source);
    }

    @Override
    @Transactional
    public void deleteAllAmenitiesFromRoom(int idRoom) {
        String sql = "delete from room_amenity where idRoom = :idRoom";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("idRoom", idRoom);
        jdbc.update(sql, source);
    }
}
