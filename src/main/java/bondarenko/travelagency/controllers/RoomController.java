package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.Facility;
import bondarenko.travelagency.models.Hotel;
import bondarenko.travelagency.models.Room;
import bondarenko.travelagency.repositories.RoomRepository;
import bondarenko.travelagency.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ImageService imageService;
    @GetMapping("/hotel/room/{idRoom}")
    public String getFacility(@PathVariable int idRoom, Model model) {
        model.addAttribute("room", roomRepository.getRoomById(idRoom));
        model.addAttribute("images", imageService.getListImagesByParentId(idRoom, "room_image"));
        return "room";
    }

    @GetMapping("/hotel/room/add/{idHotel}")
    public String addFacility(@PathVariable int idHotel, Model model) {
        model.addAttribute("idHotel", idHotel);
        return "add-room";
    }

    @PostMapping("/room/add")
    public String addFacility(@ModelAttribute("room") Room room) {
        roomRepository.addRoom(room);
        return "redirect:/hotel/" + room.getIdHotel();
    }

    @PostMapping("/room/update")
    public String updateFacility(@ModelAttribute("room") Room room) {
        roomRepository.updateRoom(room);
        return "redirect:/hotel/room/" + room.getIdRoom();
    }

    @PostMapping("/room/delete")
    public String deleteFacility(@ModelAttribute("room") Room room) {
        roomRepository.deleteRoom(room.getIdRoom());
        return "redirect:/hotel/" + room.getIdHotel();
    }

}
