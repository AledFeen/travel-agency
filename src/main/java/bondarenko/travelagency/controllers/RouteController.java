package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.Route;
import bondarenko.travelagency.repositories.ReservationRepository;
import bondarenko.travelagency.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RouteController {
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/routes")
    public String getRoutes(Model model) {
        model.addAttribute("routes", routeRepository.getRouteList());
        return "routes";
    }

    @GetMapping("/route/add")
    public String addRoute() {
        return "add-route";
    }

    @PostMapping("/route/add")
    public String Add(@ModelAttribute("route") Route route){
        routeRepository.addRoute(route);
        return "redirect:/routes";
    }

    @GetMapping("/route/{routeId}")
    public String getRoute(@PathVariable("routeId") int id, Model model) {
        model.addAttribute("route", routeRepository.getRouteById(id));
        model.addAttribute("reservations", reservationRepository.getReservationListByRouteId(id));
        return "route";
    }

    @PostMapping("/route/update")
    public String updateRoute(@ModelAttribute("route") Route route) {
        routeRepository.updateRoute(route);
        return "redirect:/route/" + route.getIdRoute();
    }
}
