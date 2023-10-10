package bondarenko.travelagency.services;

import bondarenko.travelagency.models.Amenity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmenityService {
    public List<Amenity> filterListForAdding(List<Amenity> list1, List<Amenity> list2) {
        List<Amenity> resultList = new ArrayList<>();
        for (Amenity obj1 : list1) {
            boolean foundMatch = false;

            for (Amenity obj2 : list2) {
                if (obj1.getIdAmenity() == obj2.getIdAmenity() && obj1.getName().equals(obj2.getName())) {
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
