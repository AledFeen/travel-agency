package bondarenko.travelagency.models;

import lombok.Data;

@Data
public class HotelFoodType {
    int id;
    int idHotel;
    int idFoodType;
    int price;
}
