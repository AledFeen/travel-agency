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
    @GetMapping("/image/{id}")
    private ResponseEntity<?> getImageById(@PathVariable int id) {
        Image image = imageService.findImageById(id);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

    @PostMapping("/image/add")
    private String updateImage(@RequestParam ("idHotel") int idHotel, @RequestParam ("file") MultipartFile file) throws IOException, SQLException {
        boolean isSaved = imageService.save(file, idHotel);
        return "redirect:/hotel/" + idHotel;
    }

    @PostMapping("/image/delete/{id}")
    private String deleteImage(@PathVariable("id") int id, @RequestParam ("idHotel") int idHotel) {
        imageService.deleteImageById(id);
        return "redirect:/hotel/" + idHotel;
    }

}
