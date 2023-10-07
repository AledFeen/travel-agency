package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.Image;

import java.sql.SQLException;
import java.util.List;

public interface ImageRepository {
    List<Image> findAllImagesByIdHotel(int idHotel);
    Image findImageById(int idImage);
    void saveImage(Image image) throws SQLException;
    void deleteImageById(int id);
}
