package bondarenko.travelagency.services;

import bondarenko.travelagency.models.Amenity;
import bondarenko.travelagency.models.FoodType;
import bondarenko.travelagency.models.HotelFoodType;
import bondarenko.travelagency.models.dto.FoodTypeDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FoodTypeService {
    public List<FoodType> filterListForAdding(List<FoodType> foodTypes, List<FoodTypeDto> hotelFoodTypes) {
        List<FoodType> resultList = new ArrayList<>();
        for (FoodType obj1 : foodTypes) {
            boolean foundMatch = false;

            for (FoodTypeDto obj2 : hotelFoodTypes) {
                if (obj1.getId() == obj2.getIdFoodType()) {
                    foundMatch = true;
                    break;
                }
            }

            if (!foundMatch) {
                resultList.add(obj1);
            }
        }
        return resultList;
    }
}
