package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.Facility;
import bondarenko.travelagency.models.Hotel;
import bondarenko.travelagency.models.Image;
import bondarenko.travelagency.models.dto.EstablishmentDto;
import bondarenko.travelagency.repositories.HotelRepository;
import bondarenko.travelagency.repositories.RoomRepository;
import bondarenko.travelagency.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/admin")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    ImageService imageService;
    @Autowired
    RoomRepository roomRepository;

    //////////////////////// Hotels
    @GetMapping("/hotels")
    public String getHotels(Model model){
        model.addAttribute("hotels",hotelRepository.getHotelList());
        return "hotels";
    }

    @GetMapping("/hotel/{id}")
    public String getHotel(@PathVariable int id, Model model){
        model.addAttribute("hotel", hotelRepository.getHotelById(id));
        model.addAttribute("establishList", hotelRepository.getEstablishListByHotelId(id));
        model.addAttribute("facilities", hotelRepository.getFacilitiesByHotelId(id));
        model.addAttribute("images", imageService.getListImagesByParentId(id, "hotel_image"));
        model.addAttribute("rooms", roomRepository.getRoomsByHotelId(id));
        return "hotel";
    }

    @PostMapping("/hotel/update")
    public String updateFacility(@ModelAttribute("hotel") Hotel hotel) {
        hotelRepository.updateHotel(hotel);
        return "redirect:/hotel/" + hotel.getId();
    }

    @GetMapping("/hotel/add")
    public String addHotel(){
        return "add-hotel";
    }

    @PostMapping("/hotel/add")
    public String addHotel(@ModelAttribute("hotel") Hotel hotel) {
        Hotel returnedHotel = hotelRepository.addHotel(hotel);
        return "redirect:/hotel/" + returnedHotel.getId();
    }

}
