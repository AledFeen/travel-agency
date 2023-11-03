package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.Image;
import bondarenko.travelagency.repositories.ImageRepository;
import bondarenko.travelagency.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
public class ImageController {
    @Autowired
    private final ImageService imageService;
    @GetMapping("/imageHotel/{id}")
    private ResponseEntity<?> getHotelImageById(@PathVariable int id) {
        Image image = imageService.findHotelImageById(id);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
    @GetMapping("/imageRoom/{id}")
    private ResponseEntity<?> getRoomImageById(@PathVariable int id) {
        Image image = imageService.findRoomImageById(id);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
    @GetMapping("/imageRoute/{id}")
    private ResponseEntity<?> getRouteImageById(@PathVariable int id) {
        Image image = imageService.findRouteImageById(id);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

    @PostMapping("/image/add")
    private String addImage(@RequestParam ("idParent") int idParent, @RequestParam ("table") String table, @RequestParam ("file") MultipartFile file) throws IOException, SQLException {
        boolean isSaved = imageService.save(file, idParent, table);
        if (table.equals("room_image")) {
            return "redirect:/hotel/room/" + idParent;
        } else if(table.equals("hotel_image")) {
            return "redirect:/hotel/" + idParent;
        } else {
            return "redirect:/route/" + idParent;
        }
    }

    @PostMapping("/image/delete/{id}")
    private String deleteImage(@PathVariable("id") int id, @RequestParam ("table") String table, @RequestParam ("idParent") int idParent) {
        imageService.deleteImageById(id, table);
        if (table.equals("room_image")) {
            return "redirect:/hotel/room/" + idParent;
        } else if(table.equals("hotel_image")) {
            return "redirect:/hotel/" + idParent;
        } else {
            return "redirect:/route/" + idParent;
        }
    }
}
