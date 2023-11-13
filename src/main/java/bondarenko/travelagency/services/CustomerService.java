package bondarenko.travelagency.services;

import bondarenko.travelagency.models.Amenity;
import bondarenko.travelagency.models.Reservation;
import bondarenko.travelagency.models.Room;
import bondarenko.travelagency.models.dto.ChangedList;
import bondarenko.travelagency.models.dto.ReservationDto;
import bondarenko.travelagency.models.dto.RoomDto;
import bondarenko.travelagency.models.dto.RouteDto;
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
    @Autowired
    ReservationRepository reservationRepository;

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

    public List<ReservationDto> getDtoReservationsNoImage(List<Reservation> reservations) {
        List<ReservationDto> list = new ArrayList<>();
        for(Reservation item : reservations) {
            ReservationDto resDto = new ReservationDto();
            resDto.setReservation(item);
            resDto.setHotel(customerRepository.getHotelByReservationId(item.getId()));
            resDto.setFacilityList(hotelRepository.getFacilitiesByHotelId(item.getIdHotel()));
            resDto.setEstablishmentList(hotelRepository.getEstablishListByHotelId(item.getIdHotel()));
            resDto.setFirm(customerRepository.getFirmByHotelId(item.getIdHotel()));
            resDto.setCountry(customerRepository.getCountryByFirmId(resDto.getFirm().getIdGroup()));
            resDto.setFoodTypes(foodTypeRepository.getFoodTypeByHotelId(item.getIdHotel()));

            List<RoomDto> rooms = new ArrayList<>();
            for(Room room : roomRepository.getRoomsByHotelId(item.getIdHotel())) {
                RoomDto roomDto = new RoomDto();
                roomDto.setRoom(room);
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

    public List<RouteDto> getRoutes() {
        List<RouteDto> resultList = new ArrayList<>();
        var routes = routeRepository.getRouteList();
        for(var item : routes) {
            var reservations = reservationRepository.getReservationListByRouteId(item.getIdRoute());
            RouteDto routeDto = new RouteDto();
            routeDto.setRoute(item);
            routeDto.setReservations(getDtoReservationsNoImage(reservations));
            resultList.add(routeDto);
        }
        return resultList;
    }

    public List<RouteDto> getRoutes(List<String> attributes) {
        List<RouteDto> firstList = new ArrayList<>();
        var routes = routeRepository.getRouteList();
        for(var item : routes) {
            var reservations = reservationRepository.getReservationListByRouteId(item.getIdRoute());
            RouteDto routeDto = new RouteDto();
            routeDto.setRoute(item);
            routeDto.setReservations(getDtoReservationsNoImage(reservations));
            firstList.add(routeDto);
        }
        List<String> countries = new ArrayList<>();
        List<Integer> ranks = new ArrayList<>();
        List<String> types = new ArrayList<>();

        for(var item : attributes) {
            if(item.equals("single") || item.equals("multi")) {
                types.add(item);
            } else if (item.equals("1") ||  item.equals("2") || item.equals("3") || item.equals("4") || item.equals("5")) {
                ranks.add(Integer.parseInt(item));
            } else {
                for(var c : customerRepository.getCountries()) {
                    if(item.equals(c.getCountryName())) countries.add(item);
                }
            }
        }

        List<RouteDto> secondList;

        if(!types.isEmpty()) {
            secondList = new ArrayList<>();
            for(var item : firstList) {
                if(item.getReservations().size() == 1 && types.contains("single")) {
                    secondList.add(item);
                } else if(item.getReservations().size() > 1 && types.contains("multi")) {
                    secondList.add(item);
                }
            }
        } else secondList = firstList;

        List<RouteDto> thirdList;
        if(!countries.isEmpty()) {
            thirdList = new ArrayList<>();
            for(var item : secondList) {
                boolean isCheck = false;
                for(var res : item.getReservations()) {
                    if(countries.contains(res.getCountry().getCountryName())) {
                        isCheck = true;
                    }
                }
                if(isCheck) {
                    thirdList.add(item);
                    isCheck = false;
                }
            }
        } else thirdList = secondList;

        List<RouteDto> fourthList;
        if(!ranks.isEmpty()) {
            fourthList = new ArrayList<>();
            for(var item : thirdList) {
                boolean isCheck = false;
                for(var res : item.getReservations()) {
                    if(ranks.contains(res.getHotel().getRank())) {
                        isCheck = true;
                    }
                }
                if(isCheck) {
                    fourthList.add(item);
                    isCheck = false;
                }
            }
        } else fourthList = thirdList;

        return fourthList;
    }
}
