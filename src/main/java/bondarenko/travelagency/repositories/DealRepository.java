package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.dto.ChangedDto;
import bondarenko.travelagency.models.dto.DealDto;
import bondarenko.travelagency.models.mappers.ChangedDtoMapper;
import bondarenko.travelagency.models.mappers.DealDtoMapper;
import bondarenko.travelagency.models.mappers.FoodTypeDtoMapper;
import bondarenko.travelagency.models.mappers.FoodTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
public class DealRepository {
    private final NamedParameterJdbcTemplate jdbc;

    public List<DealDto> getAllDeals() {
        String sql = "select d.idDeal, d.idRoute, r.rName, d.userLogin, d.totalPrice, d.idStatus, s.statusName, d.idStartPlace, sp.city, sp.type, d.phoneNumber, d.email from deal d \n" +
                "inner join status s on d.idStatus = s.idStatus\n" +
                "inner join startPlace sp on d.idStartPlace = sp.idStartPlace\n" +
                "inner join route r on d.idRoute = r.idRoute";
        return jdbc.query(sql, new DealDtoMapper());
    }

    public List<ChangedDto> getAllChoicesByDealId(int idDeal) {
        String sql = "select d.idChanged, d.idDeal, d.idReservation, r.idHotel, d.idFoodType, d.idRoom from `changed` d\n" +
                "inner join reservation r on d.idReservation = r.idReservation where idDeal = :idDeal";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("idDeal", idDeal);
        return jdbc.query(sql, source, new ChangedDtoMapper());
    }
    @Transactional
    public void deleteDeal(int idDeal) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("idDeal", idDeal);
        String sql = "Delete from changed where idDeal = :idDeal";
        jdbc.update(sql, source);
        sql = "Delete from deal where idDeal = :idDeal";
        jdbc.update(sql, source);
    }

}
