package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.Facility;
import bondarenko.travelagency.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/admin")
public class FacilityController {
    @Autowired
    HotelRepository hotelRepository;
    //////////////////////// Facility
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
        hotelRepository.addFacilityToHotel(facility);
        return "redirect:/admin/hotel/facility/add/" + facility.getIdHotel().toString();
    }

    @PostMapping("/facility/update")
    public String updateFacility(@ModelAttribute("facility") Facility facility) {
        hotelRepository.updateFacility(facility);
        return "redirect:/admin/hotel/facility/" + facility.getIdFacility().toString();
    }

    @PostMapping("/facility/delete")
    public String deleteFacility(@ModelAttribute("facility") Facility facility) {
        hotelRepository.deleteFacility(facility);
        return "redirect:/admin/hotel/" + facility.getIdHotel().toString();
    }
}
