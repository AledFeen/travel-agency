package bondarenko.travelagency.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    Integer id;
    String hotelName;
    Integer rank;
    String city;
    String location;
    Integer idGroup;
}
