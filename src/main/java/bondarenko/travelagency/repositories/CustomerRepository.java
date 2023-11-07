package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.*;
import bondarenko.travelagency.models.dto.ChangedList;
import bondarenko.travelagency.models.mappers.*;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

@Repository
@AllArgsConstructor
public class CustomerRepository {
    private final NamedParameterJdbcTemplate jdbc;
    public Hotel getHotelByReservationId(int idRepo) {
        String sql = "select h.* from hotel h inner join reservation r on h.idHotel = r.idHotel where idReservation = :id LIMIT 1";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", idRepo);
        return jdbc.queryForObject(sql, source, new HotelMapper());
    }

    public Firm getFirmByHotelId(int idHotel) {
        String sql="select f.* from firm f inner join hotel h on h.idGroup = f.idGroup where h.idHotel = :id LIMIT 1";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", idHotel);
        return jdbc.queryForObject(sql, source, new FirmMapper());
    }

    public Country getCountryByFirmId(int idFirm) {
        String sql ="select c.* from country c inner join firm f on c.idCountry = f.idCountry where f.idGroup = :id LIMIT 1";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", idFirm);
        return jdbc.queryForObject(sql, source, new CountryMapper());
    }

    public List<StartPlace> getStartPlaces() {
        String sql ="select * from startPlace";
        MapSqlParameterSource source = new MapSqlParameterSource();
        return jdbc.query(sql, source, new StartPlaceMapper());
    }
    @Transactional
    public void saveDeal(ChangedList changedList, int idRoute, int startPlace, String username, int totalPrice, String phone) {
        String sql ="insert into deal(idRoute, userLogin, totalPrice, idStatus, idStartPlace, phoneNumber) values(:idRoute, :username, :totalPrice, 1, :startPlace, :phone)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("idRoute", idRoute);
        source.addValue("startPlace", startPlace);
        source.addValue("username", username);
        source.addValue("totalPrice", totalPrice);
        source.addValue("phone", phone);
        jdbc.update(sql, source);

        sql = "SELECT LAST_INSERT_ID() as last_num LIMIT 1;";
        LastNum idDeal = jdbc.queryForObject(sql, new MapSqlParameterSource(), new LastIndexMapper());

        for (var item : changedList.getChangeDtoList()) {
            sql = "INSERT INTO changed(idDeal, idReservation, idFoodType, idRoom) VALUES (:idDeal, :idReserv, :idFt, :idRoom)";
            source = new MapSqlParameterSource();
            assert idDeal != null;
            source.addValue("idDeal", idDeal.getNum());
            source.addValue("idReserv", item.getIdReservation());
            source.addValue("idFt", item.getIdFoodType());
            source.addValue("idRoom", item.getIdRoom());
            jdbc.update(sql, source);
        }
    }
}
