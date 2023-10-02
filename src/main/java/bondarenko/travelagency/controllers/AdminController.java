package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.Facility;
import bondarenko.travelagency.models.Hotel;
import bondarenko.travelagency.models.dto.EstablishmentDto;
import bondarenko.travelagency.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/admin")
public class AdminController {
    @Autowired
    HotelRepository hotelRepository;
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

    @GetMapping("/hotel/facility/{idFacility}")
    public String getFacility(@PathVariable int idFacility, Model model) {
        model.addAttribute("facility", hotelRepository.getFacilityById(idFacility));
        return "facility";
    }

    @GetMapping("/hotel/facility/add/{idHotel}")
    public String addFacility(@PathVariable int idHotel, Model model) {
        model.addAttribute("idHotel", idHotel);
        return "add-facility";
    }

    @PostMapping("/facility/add")
    public String addFacility(@ModelAttribute("facility") Facility facility, @RequestParam("idHotel") int id) {
        facility.setIdHotel(id);
        hotelRepository.addFacilityToHotel(facility);
        return "redirect:/hotel/facility/add/" + id;
    }






    @GetMapping("/hotel/establishment/add/{idHotel}")
    public String addEstablishment(@PathVariable int idHotel, Model model) {
        model.addAttribute("idHotel", idHotel);
        return "add-hotel";
    }

    @PostMapping("/establishment/add")
    public String addEstablishment(@ModelAttribute("establish") EstablishmentDto establish, @RequestParam("idHotel") int id) {
        return "redirect:/hotel/" + id;
    }
}
