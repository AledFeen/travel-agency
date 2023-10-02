package bondarenko.travelagency.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facility {
    Integer idFacility;
    String facilityName;
    String facilityDescription;
    Integer idHotel;
}
