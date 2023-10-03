package bondarenko.travelagency.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentDto {
    int idEstablishment;
    String name;
    String description;
    int idHotel;
    String type;
}
