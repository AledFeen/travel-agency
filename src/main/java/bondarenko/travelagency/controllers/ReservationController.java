package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.Reservation;
import bondarenko.travelagency.repositories.HotelRepository;
import bondarenko.travelagency.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservationController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/reservation/add/{idRoute}")
    public String getReservationAddPage(@PathVariable("idRoute") int idRoute, Model model) {
        model.addAttribute("hotels", hotelRepository.getHotelList());
        model.addAttribute("id_route", idRoute);
        return "add-reservation";
    }

    @PostMapping("/reservation/add")
    public String addReservation(@ModelAttribute("reservation") Reservation reservation, @RequestParam("idRoute") int idRoute) {
        reservationRepository.addReservation(reservation, idRoute);
        return "redirect:/route/" + idRoute;
    }

    @PostMapping("/reservation/delete")
    public String deleteReservarion(@RequestParam("idReservation") int idReserv, @RequestParam("idRoute") int idRoute) {
        reservationRepository.deleteReservation(idReserv);
        return "redirect:/route/" + idRoute;
    }
}
