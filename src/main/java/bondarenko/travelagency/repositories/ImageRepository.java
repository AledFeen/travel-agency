package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.Image;
import bondarenko.travelagency.models.dto.ImageForFindDto;

import java.sql.SQLException;
import java.util.List;

public interface ImageRepository {
    List<Image> findAllImagesByIdParent(int idHotel, String table);
    Image findHotelImageById(int idImage);
    Image findRoomImageById(int idImage);
    Image findRouteImageById(int idImage);
    void saveImage(Image image, String table) throws SQLException;
    void deleteImageById(int id, String table);
     List<ImageForFindDto> getImagesForFind(String table_name);
}
