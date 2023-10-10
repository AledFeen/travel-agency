package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = new Room();
        room.setIdRoom(rs.getInt("idRoom"));
        room.setBed(rs.getString("bed"));
        room.setClas(rs.getString("class"));
        room.setRoomsCount(rs.getString("roomsCount"));
        room.setCleaning(rs.getString("cleaning"));
        room.setDailyPrice(rs.getFloat("dailyPrice"));
        room.setIdHotel(rs.getInt("idHotel"));
        return room;
    }
}
