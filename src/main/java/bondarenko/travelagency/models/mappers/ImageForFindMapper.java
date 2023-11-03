package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.dto.ImageForFindDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageForFindMapper implements RowMapper<ImageForFindDto> {
    @Override
    public ImageForFindDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ImageForFindDto image = new ImageForFindDto();
        image.setIdImage(rs.getInt("idImage"));
        image.setIdParent(rs.getInt("idParent"));
        return image;
    }
}
