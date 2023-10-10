package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.Room;
import bondarenko.travelagency.models.mappers.RoomMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@AllArgsConstructor
public class RoomRepositoryImpl implements RoomRepository {
    private final NamedParameterJdbcTemplate jdbc;
    @Override
    @Transactional
    public void addRoom(Room room) {
        String sql = "INSERT INTO room (bed, `class`, roomsCount, cleaning, dailyPrice, idHotel)" +
                "Values (:bed, :class, :roomsCount, :cleaning, :dailyPrice, :idHotel)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("bed", room.getBed());
        source.addValue("class", room.getClas());
        source.addValue("roomsCount", room.getRoomsCount());
        source.addValue("cleaning", room.getCleaning());
        source.addValue("dailyPrice", room.getDailyPrice());
        source.addValue("idHotel", room.getIdHotel());
        jdbc.update(sql, source);
    }

    @Override
    public List<Room> getRoomsByHotelId(int idHotel) {
        String sql = "SELECT * from room where idHotel = :id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", idHotel);
        return jdbc.query(sql, source, new RoomMapper());
    }

    @Override
    public Room getRoomById(int id) {
        String sql = "SELECT * from room where idRoom = :id LIMIT 1";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        return jdbc.queryForObject(sql, source, new RoomMapper());
    }

    @Override
    @Transactional
    public void updateRoom(Room room) {
        String sql = "UPDATE room set bed = :bed, `class` = :class, roomsCount = :roomsCount," +
                " cleaning = :cleaning, dailyPrice = :dailyPrice" +
                " where idRoom = :idRoom";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("idRoom", room.getIdRoom());
        source.addValue("bed", room.getBed());
        source.addValue("class", room.getClas());
        source.addValue("roomsCount", room.getRoomsCount());
        source.addValue("cleaning", room.getCleaning());
        source.addValue("dailyPrice", room.getDailyPrice());
        jdbc.update(sql, source);
    }

    @Override
    @Transactional
    public void deleteRoom(int id) {
        String sql = "DELETE FROM room_image WHERE idParent = :id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        jdbc.update(sql, source);
        sql = "DELETE FROM room WHERE idRoom = :id";
        jdbc.update(sql, source);
    }
}
