package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.*;
import bondarenko.travelagency.models.dto.EstablishmentDto;
import bondarenko.travelagency.models.dto.FoodTypeDto;
import bondarenko.travelagency.repositories.FoodTypeRepository;
import bondarenko.travelagency.repositories.HotelRepository;
import bondarenko.travelagency.repositories.RoomRepository;
import bondarenko.travelagency.services.FoodTypeService;
import bondarenko.travelagency.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    ImageService imageService;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    FoodTypeRepository foodTypeRepository;
    @Autowired
    FoodTypeService foodTypeService;

    //////////////////////// Hotels

    @GetMapping("/hotels")
    public String getHotels(Model model){
        model.addAttribute("hotels",hotelRepository.getHotelList());
        return "hotels";
    }

    @GetMapping("/hotel/{id}")
    public String getHotel(@PathVariable int id, Model model) {
        model.addAttribute("hotel", hotelRepository.getHotelById(id));
        model.addAttribute("establishList", hotelRepository.getEstablishListByHotelId(id));
        model.addAttribute("facilities", hotelRepository.getFacilitiesByHotelId(id));
        model.addAttribute("images", imageService.getListImagesByParentId(id, "hotel_image"));
        model.addAttribute("rooms", roomRepository.getRoomsByHotelId(id));
        List<FoodTypeDto> hotelfoodtypes = foodTypeRepository.getFoodTypeByHotelId(id);
        model.addAttribute("hotelfoodtypes", hotelfoodtypes);
        List<FoodType> foodTypes = foodTypeRepository.getFoodTypes();
        model.addAttribute("foodtypes", foodTypeService.filterListForAdding(foodTypes, hotelfoodtypes) );
        return "hotel";
    }

    @PostMapping("/hotel/update")
    public String updateFacility(@ModelAttribute("hotel") Hotel hotel) {
        hotelRepository.updateHotel(hotel);
        return "redirect:/admin/hotel/" + hotel.getId();
    }

    @GetMapping("/hotel/add")
    public String addHotel(){
        return "add-hotel";
    }

    @PostMapping("/hotel/add")
    public String addHotel(@ModelAttribute("hotel") Hotel hotel) {
        Hotel returnedHotel = hotelRepository.addHotel(hotel);
        return "redirect:/admin/hotel/" + returnedHotel.getId();
    }

    @PostMapping("/hotel/foodtype/add")
    public String addFoodType(@ModelAttribute("foodtype") HotelFoodType hotelFoodType) {
        foodTypeRepository.addHotelFoodType(hotelFoodType);
        return "redirect:/admin/hotel/" + hotelFoodType.getIdHotel();
    }

    @PostMapping("hotel/foodtype/delete")
    public String deleteFoodType(@RequestParam("idHotel") int idHotel, @RequestParam("idHotelFoodType") int id ) {
        foodTypeRepository.deleteHotelFoodType(id);
        return "redirect:/admin/hotel/" + idHotel;
    }
}
