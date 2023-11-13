package bondarenko.travelagency.models.dto;

import bondarenko.travelagency.models.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {
    Route route;
    List<ReservationDto> reservations = new ArrayList<>();
}
