package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.dto.ChangeDto;
import bondarenko.travelagency.models.dto.ChangedList;
import bondarenko.travelagency.models.dto.ReservationDto;
import bondarenko.travelagency.repositories.CustomerRepository;
import bondarenko.travelagency.repositories.ReservationRepository;
import bondarenko.travelagency.repositories.ReviewRepository;
import bondarenko.travelagency.repositories.RouteRepository;
import bondarenko.travelagency.services.CustomerService;
import bondarenko.travelagency.services.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    RouteRepository routeRepository;
    @Autowired
    ImageService imageService;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ReviewRepository reviewRepository;


    @GetMapping("/")
    public String index(Model model) {
        //model.addAttribute("routes", routeRepository.getRouteList());
        model.addAttribute("routes", customerService.getRoutes(0));
        model.addAttribute("images", imageService.getImageForFindList("route_image"));
        return "main-page";
    }

    @GetMapping("/route/{routeId}")
    public String getRoute(@PathVariable("routeId") int id, Model model, Principal principal) {
        model.addAttribute("route", routeRepository.getRouteById(id));
        model.addAttribute("images", imageService.getListImagesByParentId(id, "route_image"));
        List<ReservationDto> reservations = customerService.getReservationDtoList(reservationRepository.getReservationListByRouteId(id));
        model.addAttribute("reviews", reviewRepository.getReviewsByRouteId(id));
        if(principal != null) {
            model.addAttribute("username", principal.getName());
        }

        model.addAttribute("dto", reservations);
        ChangedList changedList = new ChangedList();
        for(var item : reservations) {
            changedList.addChangeItem(new ChangeDto());
        }
        model.addAttribute("changedList", changedList);
        return "customer-route";
    }

    @GetMapping("/{listAttribute}")
    public String filtered_index(@PathVariable(name = "listAttribute", required = false) List<String> listAttribute,
                           Model model) {
        model.addAttribute("routes", customerService.getRoutes(listAttribute, 0));
        model.addAttribute("images", imageService.getImageForFindList("route_image"));
        return "main-page";
    }
    @GetMapping("/login")
    public String login(){ return "login"; }
    @GetMapping("/index") //future profile
    public String home(Model model, Principal principal){
        return "index";
    }

    @PostMapping("/changeForm")
    public String submitForm(@ModelAttribute("changedList") ChangedList changedList,
                             @RequestParam("idRoute") int idRoute, Model model) {
        System.out.println("Проверка списка");
        for (ChangeDto item : changedList.getChangeDtoList()) {
            System.out.println(item.getIdRoom() + " ft " + item.getIdFoodType() + " reservation " + item.getIdReservation());
        }
        model.addAttribute(changedList);
        model.addAttribute("idRoute", idRoute);
        model.addAttribute("startPlaces", customerRepository.getStartPlaces());
        return "make-deal";
    }

    @PostMapping("/makeDeal")
    public String makeDeal(@ModelAttribute("changedList") ChangedList changedList,
                           @RequestParam("idRoute") int id,
                           @RequestParam("startPlace") int startPlace,
                           @RequestParam("phoneNumber") String phone,
                           @RequestParam("email") String email,
                           Principal principal) {
        customerService.saveDeal(changedList, id, startPlace, principal.getName(), phone, email);
        return "redirect:/";
    }
}


