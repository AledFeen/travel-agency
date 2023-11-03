package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.FoodType;
import bondarenko.travelagency.models.Reservation;

import java.util.List;

public interface ReservationRepository {
    void addReservation(Reservation reservation, int idRoute);
    void deleteReservation(int idReservation);
    List<Reservation> getReservationListByRouteId(int idRoute);

}
