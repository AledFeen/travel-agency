package bondarenko.travelagency.models.dto;

import bondarenko.travelagency.models.*;
import lombok.Data;

import java.util.List;

@Data
public class ReservationDto {
    Reservation reservation;
    Hotel hotel;
    List<Facility> facilityList;
    List<EstablishmentDto> establishmentList;
    List<Image> hotelImages;
    Firm firm;
    Country country;
    List<RoomDto> rooms;
    List<FoodTypeDto> foodTypes;
}
