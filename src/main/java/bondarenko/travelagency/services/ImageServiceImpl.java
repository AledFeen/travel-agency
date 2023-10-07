package bondarenko.travelagency.services;

import bondarenko.travelagency.models.Image;
import bondarenko.travelagency.repositories.ImageRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@Service
@Data
public class ImageServiceImpl implements ImageService {
    @Autowired
    private final ImageRepository imageRepository;
    @Override
    public boolean save(MultipartFile file, int idHotel) throws IOException, SQLException {
        Image image = new Image();
        if(file.getSize() != 0 ){
            image.setPreviewImage(false);
            image.setIdHotel(idHotel);
            image.setName(file.getName());
            image.setOriginalFileName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            image.setBytes(file.getBytes());
            imageRepository.saveImage(image);
            return true;
        } else return false;
    }

    @Override
    public List<Image> getListImagesByHotelId(int id) {
        return imageRepository.findAllImagesByIdHotel(id);
    }

    @Override
    public void deleteImageById(int id) {
        imageRepository.deleteImageById(id);
    }

    @Override
    public Image findImageById(int id) {
        return imageRepository.findImageById(id);
    }
}
