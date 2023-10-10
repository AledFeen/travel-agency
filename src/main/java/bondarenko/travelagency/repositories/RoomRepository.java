package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.Hotel;
import bondarenko.travelagency.models.Room;

import java.util.List;

public interface RoomRepository {
    void addRoom(Room room);
    List<Room> getRoomsByHotelId(int idHotel);
    Room getRoomById(int id);
    void updateRoom(Room room);
    void deleteRoom(int id);
}
