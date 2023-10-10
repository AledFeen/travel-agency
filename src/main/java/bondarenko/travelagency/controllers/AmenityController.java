package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.Amenity;
import bondarenko.travelagency.repositories.AmenityRepository;
import bondarenko.travelagency.services.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AmenityController {
    @Autowired
    AmenityRepository amenityRepository;
    private final AmenityService amenityService;

    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    @GetMapping("/room/amenities/{idRoom}")
    public String getAmenitiesPage(@PathVariable("idRoom") int idRoom,
                                   Model model) {
        List<Amenity> allAmenities = amenityRepository.getAmenities();
        List<Amenity> roomAmenities =  amenityRepository.getRoomAmenities(idRoom);
        model.addAttribute("allAmenities", allAmenities);
        model.addAttribute("roomAmenities",roomAmenities);
        model.addAttribute("filteredAmenities", amenityService.filterListForAdding(allAmenities, roomAmenities));
        return "amenities";
    }

    @PostMapping("/amenity/add")
    public String addAmenity(@RequestParam ("idRoom") int idRoom,
                             @ModelAttribute ("amenity") Amenity amenity) {
        amenityRepository.addAmenity(amenity);
        return "redirect:/room/amenities/" + idRoom;
    }

    @PostMapping("amenity/delete")
    public String deleteAmenity(@RequestParam ("idRoom") int idRoom,
                                @ModelAttribute ("amenity") Amenity amenity) {
        amenityRepository.deleteAmenity(amenity.getIdAmenity());
        return "redirect:/room/amenities/" + idRoom;
    }

    @PostMapping("room/amenity/add")
    public String addAmenityForRoom(@RequestParam ("idRoom") int idRoom,
                             @ModelAttribute ("amenity") Amenity amenity) {
        amenityRepository.addAmenityForRoom(amenity.getIdAmenity(), idRoom);
        return "redirect:/room/amenities/" + idRoom;
    }

    @PostMapping("room/amenity/delete-all")
    public String deleteAllAmenityForRoom(@RequestParam ("idRoom") int idRoom,
                                    @ModelAttribute ("amenity") Amenity amenity) {
        amenityRepository.deleteAllAmenitiesFromRoom(idRoom);
        return "redirect:/room/amenities/" + idRoom;
    }

    @PostMapping("room/amenity/delete")
    public String deleteAmenityForRoom(@RequestParam ("idRoom") int idRoom,
                                          @ModelAttribute ("amenity") Amenity amenity) {
        amenityRepository.deleteAmenityFromRoom(amenity.getIdAmenity(), idRoom);
        return "redirect:/room/amenities/" + idRoom;
    }



}
