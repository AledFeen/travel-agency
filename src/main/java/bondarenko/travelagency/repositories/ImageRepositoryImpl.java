package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.Image;
import bondarenko.travelagency.models.mappers.ImageMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.util.List;
@Repository
@AllArgsConstructor
public class ImageRepositoryImpl implements ImageRepository {
    private final NamedParameterJdbcTemplate jdbc;
    @Override
    public List<Image> findAllImagesByIdHotel(int idHotel) {
        String sql = "Select * from hotel_image where idHotel = :idHotel";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idHotel", idHotel);
        return jdbc.query(sql, parameterSource, new ImageMapper());
    }

    @Override
    public Image findImageById(int idImage) {
        String sql = "Select * from hotel_image where idHotelImage = :id LIMIT 1";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", idImage);
        return jdbc.queryForObject(sql, parameterSource, new ImageMapper());
    }

    @Override
    @Transactional
    public void saveImage(Image im) throws SQLException {
        String sql = "Insert into hotel_image(idHotelImage, content_type, isPreviewImage, name, original_file_name, size, bytes, idHotel) VALUES (:id, :content_type, :is_preview_image, :name, :original_file_name, :size, :bytes, :idHotel)";

            SerialBlob serialBlob = new SerialBlob(im.getBytes());
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("id", im.getId());
            parameters.addValue("bytes", serialBlob);
            parameters.addValue("content_type", im.getContentType());
            parameters.addValue("is_preview_image", im.isPreviewImage());
            parameters.addValue("name", im.getName());
            parameters.addValue("original_file_name", im.getOriginalFileName());
            parameters.addValue("size", im.getSize());
            parameters.addValue("idHotel", im.getIdHotel());
            jdbc.update(sql, parameters);
    }

    @Override
    @Transactional
    public void deleteImageById(int id) {
        String sql = "Delete from hotel_image where idHotelImage = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        jdbc.update(sql, parameterSource);
    }
}
