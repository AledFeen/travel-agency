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
    public Image findHotelImageById(int idImage) {
        String sql = "Select * from hotel_image where idImage = :id LIMIT 1";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", idImage);
        return jdbc.queryForObject(sql, parameterSource, new ImageMapper());
    }

    @Override
    public Image findRoomImageById(int idImage) {
        String sql = "Select * from room_image where idImage = :id LIMIT 1";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", idImage);
        return jdbc.queryForObject(sql, parameterSource, new ImageMapper());
    }

    @Override
    public List<Image> findAllImagesByIdParent(int idParent, String table) {
        String sql = "Select * from "+ table +" where idParent = :idParent";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idParent", idParent);
        return jdbc.query(sql, parameterSource, new ImageMapper());
    }

    @Override
    @Transactional
    public void saveImage(Image im, String table) throws SQLException {
        String sql = "Insert into "+ table +"(idImage, content_type, isPreviewImage, name, original_file_name, size, bytes, idParent) VALUES (:id, :content_type, :is_preview_image, :name, :original_file_name, :size, :bytes, :idParent)";

            SerialBlob serialBlob = new SerialBlob(im.getBytes());
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("id", im.getId());
            parameters.addValue("bytes", serialBlob);
            parameters.addValue("content_type", im.getContentType());
            parameters.addValue("is_preview_image", im.isPreviewImage());
            parameters.addValue("name", im.getName());
            parameters.addValue("original_file_name", im.getOriginalFileName());
            parameters.addValue("size", im.getSize());
            parameters.addValue("idParent", im.getIdParent());
            jdbc.update(sql, parameters);
    }

    @Override
    @Transactional
    public void deleteImageById(int id, String table) {
        String sql = "Delete from "+table+" where idImage = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        jdbc.update(sql, parameterSource);
    }
}
