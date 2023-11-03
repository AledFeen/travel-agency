package bondarenko.travelagency.models.dto;

import lombok.Data;

@Data
public class FoodTypeDto {
    int id;
    int idHotel;
    int idFoodType;
    int price;
    String name;
    String description;
}
