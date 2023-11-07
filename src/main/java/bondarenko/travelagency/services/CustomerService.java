package bondarenko.travelagency.services;

import bondarenko.travelagency.models.Amenity;
import bondarenko.travelagency.models.Reservation;
import bondarenko.travelagency.models.Room;
import bondarenko.travelagency.models.dto.ChangedList;
import bondarenko.travelagency.models.dto.ReservationDto;
import bondarenko.travelagency.models.dto.RoomDto;
import bondarenko.travelagency.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    ImageService imageService;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    AmenityRepository amenityRepository;
    @Autowired
    FoodTypeRepository foodTypeRepository;
    @Autowired
    RouteRepository routeRepository;

    public List<ReservationDto> getReservationDtoList(List<Reservation> reservations) {
        List<ReservationDto> list = new ArrayList<>();
        for(Reservation item : reservations) {
            ReservationDto resDto = new ReservationDto();
            resDto.setReservation(item);
            resDto.setHotel(customerRepository.getHotelByReservationId(item.getId()));
            resDto.setFacilityList(hotelRepository.getFacilitiesByHotelId(item.getIdHotel()));
            resDto.setEstablishmentList(hotelRepository.getEstablishListByHotelId(item.getIdHotel()));
            resDto.setHotelImages(imageService.getListImagesByParentId(item.getIdHotel(), "hotel_image"));
            resDto.setFirm(customerRepository.getFirmByHotelId(item.getIdHotel()));
            resDto.setCountry(customerRepository.getCountryByFirmId(resDto.getFirm().getIdGroup()));
            resDto.setFoodTypes(foodTypeRepository.getFoodTypeByHotelId(item.getIdHotel()));

            List<RoomDto> rooms = new ArrayList<>();
            for(Room room : roomRepository.getRoomsByHotelId(item.getIdHotel())) {
                RoomDto roomDto = new RoomDto();
                roomDto.setRoom(room);
                roomDto.setRoomImages(imageService.getListImagesByParentId(room.getIdRoom(), "room_image"));
                roomDto.setAmenities(amenityRepository.getRoomAmenities(room.getIdRoom()));
                rooms.add(roomDto);
            }
            resDto.setRooms(rooms);

            list.add(resDto);
        }
        return list;
    }

    private int countTotalPrice (ChangedList changedList, int idRoute) {
        int totalPrice = 0;

        for (var item : changedList.getChangeDtoList()) {
            totalPrice += (int) roomRepository.getRoomById(item.getIdRoom()).getDailyPrice();
            totalPrice += foodTypeRepository.getHotelFoodTypeById(item.getIdFoodType()).getPrice();
        }
        totalPrice += routeRepository.getRouteById(idRoute).getTransportPrice();
        return totalPrice;
    }

    public void saveDeal (ChangedList changedList, int idRoute, int startPlace, String username, String phone) {
        customerRepository.saveDeal(changedList, idRoute, startPlace, username, countTotalPrice(changedList, idRoute), phone);
    }
}
