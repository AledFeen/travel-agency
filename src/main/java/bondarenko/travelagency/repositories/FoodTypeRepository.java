package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.FoodType;
import bondarenko.travelagency.models.HotelFoodType;
import bondarenko.travelagency.models.dto.FoodTypeDto;

import java.util.List;

public interface FoodTypeRepository {

    List<FoodTypeDto> getFoodTypeByHotelId(int idHotel);
    FoodTypeDto getHotelFoodTypeById(int IdHft);
    void addHotelFoodType(HotelFoodType hft);
    void deleteHotelFoodType(int idHft);

    List<FoodType> getFoodTypes();
}
