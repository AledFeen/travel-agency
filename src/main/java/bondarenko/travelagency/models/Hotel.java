package bondarenko.travelagency.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    int id;
    String hotelName;
    int rank;
    String city;
    String location;
    int idGroup;
}
