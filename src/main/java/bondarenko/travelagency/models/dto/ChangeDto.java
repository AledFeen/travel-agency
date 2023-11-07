package bondarenko.travelagency.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDto {
    int idRoom;
    int idFoodType;
    int idReservation;
}
