package bondarenko.travelagency.services;

import bondarenko.travelagency.models.Image;
import bondarenko.travelagency.models.dto.ImageForFindDto;
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
    public boolean save(MultipartFile file, int idParent, String table) throws IOException, SQLException {
        Image image = new Image();
        if(file.getSize() != 0 ){
            List<ImageForFindDto> findImages = imageRepository.getImagesForFind(table);

            if(findImages.stream().anyMatch(item -> item.getIdParent() == idParent)){
                image.setPreviewImage(false);
            } else image.setPreviewImage(true);

            image.setIdParent(idParent);
            image.setName(file.getName());
            image.setOriginalFileName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            image.setBytes(file.getBytes());
            imageRepository.saveImage(image, table);
            return true;
        } else return false;
    }

    @Override
    public List<Image> getListImagesByParentId(int id, String table) {
        return imageRepository.findAllImagesByIdParent(id, table);
    }

    @Override
    public void deleteImageById(int id, String table) {
        imageRepository.deleteImageById(id, table);
    }

    @Override
    public Image findHotelImageById(int id) {
        return imageRepository.findHotelImageById(id);
    }
    @Override
    public Image findRoomImageById(int id) {
        return imageRepository.findRoomImageById(id);
    }
    @Override
    public Image findRouteImageById(int id) {
        return imageRepository.findRouteImageById(id);
    }
}
