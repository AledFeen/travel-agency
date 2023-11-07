package bondarenko.travelagency.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StartPlace {
    int idStartPlace;
    String city;
    String type;
}
