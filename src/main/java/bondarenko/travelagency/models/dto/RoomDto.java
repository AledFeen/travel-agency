package bondarenko.travelagency.models.dto;

import bondarenko.travelagency.models.Amenity;
import bondarenko.travelagency.models.Image;
import bondarenko.travelagency.models.Room;
import lombok.Data;

import java.util.List;
@Data
public class RoomDto {
    Room room;
    List<Image> roomImages;
    List<Amenity> amenities;
}
