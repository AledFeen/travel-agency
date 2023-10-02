package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.Facility;
import bondarenko.travelagency.models.Hotel;
import bondarenko.travelagency.models.dto.EstablishmentDto;

import java.util.List;

public interface HotelRepository {
    Hotel addHotel(Hotel hotel);
    List<Hotel> getHotelList();
    Hotel getHotelById(int id);
    List<EstablishmentDto> getAllEstablishList();
    List<EstablishmentDto> getEstablishListByHotelId(int idHotel);
    List<Facility> getFacilitiesByHotelId(int idHotel);

    void addFacilityToHotel(Facility facility);

    Facility getFacilityById(int idFacility);
}
