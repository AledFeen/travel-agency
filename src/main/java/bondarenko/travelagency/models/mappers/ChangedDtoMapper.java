package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.dto.ChangeDto;
import bondarenko.travelagency.models.dto.ChangedDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangedDtoMapper implements RowMapper<ChangedDto> {
    @Override
    public ChangedDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChangedDto changed = new ChangedDto();
        changed.setIdChanged(rs.getInt("idChanged"));
        changed.setIdDeal(rs.getInt("idDeal"));
        changed.setIdReservation(rs.getInt("idReservation"));
        changed.setIdHotel(rs.getInt("idHotel"));
        changed.setIdFoodType(rs.getInt("idFoodType"));
        changed.setIdRoom(rs.getInt("idRoom"));
        return changed;
    }
}
