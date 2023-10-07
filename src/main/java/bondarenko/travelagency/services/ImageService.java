package bondarenko.travelagency.services;

import bondarenko.travelagency.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ImageService {
    boolean save(MultipartFile file, int idHotel) throws IOException, SQLException;
    List<Image> getListImagesByHotelId(int id);
    void deleteImageById(int id);
    Image findImageById(int id);
}
