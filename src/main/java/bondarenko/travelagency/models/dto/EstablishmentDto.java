package bondarenko.travelagency.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentDto {
    int id;
    String name;
    String description;
    int hotelId;
    String type;
}
