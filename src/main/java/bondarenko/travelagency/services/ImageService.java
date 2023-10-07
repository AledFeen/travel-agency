package bondarenko.travelagency.services;

import bondarenko.travelagency.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ImageService {
    boolean save(MultipartFile file, int idParent, String table) throws IOException, SQLException;
    List<Image> getListImagesByParentId(int id, String table);
    void deleteImageById(int id, String table);
    Image findHotelImageById(int id);
    Image findRoomImageById(int id);
}
