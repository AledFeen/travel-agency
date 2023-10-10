package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.Amenity;

import java.util.List;

public interface AmenityRepository {
    List<Amenity> getAmenities();
    List<Amenity> getRoomAmenities(int idRoom);
    void addAmenity(Amenity amenity);
    void deleteAmenity(int idAmenity);
    void addAmenityForRoom(int idAmenity, int idRoom);
    void deleteAmenityFromRoom(int idAmenity, int idRoom);
    void deleteAllAmenitiesFromRoom(int idRoom);
}
