package bondarenko.travelagency.models.mappers;

import bondarenko.travelagency.models.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationMapper implements RowMapper<Reservation> {
    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(rs.getInt("idReservation"));
        reservation.setStartDate(rs.getDate("startData"));
        reservation.setEndDate(rs.getDate("endData"));
        reservation.setIdHotel(rs.getInt("idHotel"));
        reservation.setMinPrice(rs.getInt("minPriceReserv"));
        reservation.setMaxPrice(rs.getInt("maxPriceReserv"));
        return reservation;
    }
}
