package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.dto.EstablishmentDto;
import bondarenko.travelagency.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

@Controller
public class EstablishmentController {
    @Autowired
    HotelRepository hotelRepository;
    ////////////////////// Establishment
    @GetMapping("/hotel/establishment/{idEst}")
    public String getEstablishment(@PathVariable int idEst, Model model){
        model.addAttribute("establishment", hotelRepository.getEstablishmentById(idEst));
        model.addAttribute("types", hotelRepository.getAllEstTypes());
        return "establishment";
    }
    @GetMapping("/hotel/establishment/add/{idHotel}")
    public String addEstablishment(@PathVariable int idHotel, Model model) {
        model.addAttribute("idHotel", idHotel);
        return "add-establishment";
    }
    @PostMapping("/establishment/add")
    public String addEstablishment(@ModelAttribute("establishment") EstablishmentDto establishment)
    {
        hotelRepository.addEstablishmentToHotel(establishment);
        return "redirect:/hotel/" + establishment.getIdHotel();
    }

    @PostMapping("/establishment/update")
    public String updateEstablishment(@ModelAttribute("establishment") EstablishmentDto establishment, @RequestParam ("type") String type) {
        establishment.setType(type);
        hotelRepository.updateEstablishment(establishment);
        return "redirect:/hotel/establishment/" + establishment.getIdEstablishment();
    }

    @PostMapping("/establishment/delete")
    public String deleteEstablishment(@ModelAttribute("establishment") EstablishmentDto establishment) {
        hotelRepository.deleteEstablishment(establishment);
      return "redirect:/hotel/" + establishment.getIdHotel();
    }
}
