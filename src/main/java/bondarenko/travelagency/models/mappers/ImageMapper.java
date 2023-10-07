package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.Image;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
        Image image = new Image();
        image.setId(rs.getInt("idHotelImage"));
        image.setContentType(rs.getString("content_type"));
        image.setPreviewImage(rs.getBoolean("isPreviewImage"));
        image.setName(rs.getString("name"));
        image.setOriginalFileName(rs.getString("original_file_name"));
        image.setSize(rs.getLong("size"));
        image.setBytes(rs.getBytes("bytes"));
        image.setIdHotel(rs.getInt("idHotel"));
        return image;
    }
}
