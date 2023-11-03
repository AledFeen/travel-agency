package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.LastNum;
import bondarenko.travelagency.models.Reservation;
import bondarenko.travelagency.models.mappers.LastIndexMapper;
import bondarenko.travelagency.models.mappers.ReservationMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Repository
@AllArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {
    private final NamedParameterJdbcTemplate jdbc;
    @Override
    @Transactional
    public void addReservation(Reservation reservation, int idRoute) {
        String sql = "INSERT INTO reservation(startData, endData, idHotel, minPriceReserv, maxPriceReserv) VALUES(:start, :end, :idHotel, 0, 0)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("start", reservation.getStartDate());
        source.addValue("end", reservation.getEndDate());
        source.addValue("idHotel", reservation.getIdHotel());
        jdbc.update(sql, source);

        sql = "SELECT LAST_INSERT_ID() as last_num LIMIT 1;";
        LastNum lastNum = jdbc.queryForObject(sql, new MapSqlParameterSource(), new LastIndexMapper());

        sql = "INSERT INTO route_reservation(idRoute, idReservation) VALUES (:idRoute, :idReservation)";
        source = new MapSqlParameterSource();
        source.addValue("idRoute", idRoute);
        source.addValue("idReservation", lastNum.getNum());
        jdbc.update(sql, source);
    }


    @Override
    @Transactional
    public void deleteReservation(int idReservation) {
        MapSqlParameterSource source = new MapSqlParameterSource("id", idReservation);
        String sql = "Delete from route_reservation where idReservation = :id";
        jdbc.update(sql, source);
        sql = "Delete from reservation where idReservation = :id";
        jdbc.update(sql, source);
    }

    @Override
    public List<Reservation> getReservationListByRouteId(int idRoute) {
        String sql = "Select r.idReservation, r.startData, r.endData, r.idHotel, r.minPriceReserv, r.maxPriceReserv from reservation r inner join route_reservation rr on r.idReservation = rr.idReservation where rr.idRoute = :id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", idRoute);
        return jdbc.query(sql, source, new ReservationMapper());
    }
}
