package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.Route;
import bondarenko.travelagency.repositories.ReservationRepository;
import bondarenko.travelagency.repositories.ReviewRepository;
import bondarenko.travelagency.repositories.RouteRepository;
import bondarenko.travelagency.services.CustomerService;
import bondarenko.travelagency.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.StyledEditorKit;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class RouteController {
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ImageService imageService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping("/routes/")
    public String getRoutes(Model model) {
        model.addAttribute("routes", customerService.getRoutes(1));
        return "routes";
    }

    @GetMapping("/routes/{listAttribute}")
    public String filtered_index(@PathVariable(name = "listAttribute", required = false) List<String> listAttribute,
                                 Model model) {
        model.addAttribute("routes", customerService.getRoutes(listAttribute, 1));
        return "routes";
    }

    @GetMapping("/route/add")
    public String addRoute() {
        return "add-route";
    }

    @PostMapping("/route/add")
    public String Add(@ModelAttribute("route") Route route){
        routeRepository.addRoute(route);
        return "redirect:/admin/routes";
    }

    @GetMapping("/route/{routeId}")
    public String getRoute(@PathVariable("routeId") int id, Model model, Principal principal) {
        model.addAttribute("route", routeRepository.getRouteById(id));
        model.addAttribute("reservations", reservationRepository.getReservationListByRouteId(id));
        model.addAttribute("images", imageService.getListImagesByParentId(id, "route_image"));
        model.addAttribute("reviews", reviewRepository.getReviewsByRouteId(id));
        model.addAttribute("username", principal.getName());
        return "route";
    }

    @PostMapping("/route/update")
    public String updateRoute(@ModelAttribute("route") Route route) {
        routeRepository.updateRoute(route);
        return "redirect:/admin/route/" + route.getIdRoute();
    }
}
