package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.Facility;
import bondarenko.travelagency.models.Hotel;
import bondarenko.travelagency.models.dto.EstablishmentDto;
import bondarenko.travelagency.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/admin")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

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
        return "hotel";
    }

    @GetMapping("/hotel/add")
    public String addHotel(){
        return "add-hotel";
    }

    @PostMapping("/hotel/add")
    public String addHotel(@ModelAttribute("hotel") Hotel hotel) {
        Hotel returnedHotel = hotelRepository.addHotel(hotel);
        return "redirect:/hotel/" + returnedHotel.getId().toString();
    }

}
