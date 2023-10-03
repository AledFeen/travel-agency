package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.Facility;
import bondarenko.travelagency.models.Hotel;
import bondarenko.travelagency.models.dto.EstablishmentDto;

import java.util.List;

public interface HotelRepository {
    Hotel addHotel(Hotel hotel);
    List<Hotel> getHotelList();
    Hotel getHotelById(int id);

    List<Facility> getFacilitiesByHotelId(int idHotel);
    void addFacilityToHotel(Facility facility);
    Facility getFacilityById(int idFacility);
    void updateFacility(Facility facility);
    void deleteFacility(Facility facility);

    List<EstablishmentDto> getAllEstablishList();
    List<EstablishmentDto> getEstablishListByHotelId(int idHotel);
    void addEstablishmentToHotel(EstablishmentDto establishment);
    EstablishmentDto getEstablishmentById(EstablishmentDto establishment);
    void updateEstablishment(EstablishmentDto establishment);
    void deleteEstablishment(EstablishmentDto establishment);
}
