package bondarenko.travelagency.models;

import lombok.Data;

@Data
public class Room {
    int idRoom;
    String bed;
    String clas;
    String roomsCount;
    String cleaning;
    float dailyPrice;
    int idHotel;
}
