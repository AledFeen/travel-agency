package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.EstType;
import bondarenko.travelagency.models.Facility;
import bondarenko.travelagency.models.Hotel;
import bondarenko.travelagency.models.dto.EstablishmentDto;
import bondarenko.travelagency.models.mappers.EstTypeMapper;
import bondarenko.travelagency.models.mappers.EstablishmentDtoMapper;
import bondarenko.travelagency.models.mappers.FacilityMapper;
import bondarenko.travelagency.models.mappers.HotelMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@AllArgsConstructor
public class HotelRepositoryImpl implements HotelRepository {
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    @Transactional
    public Hotel addHotel(Hotel hotel) {
        String sql = "Insert into hotel(`hotelName`, `rank`, city, location, idGroup) " +
                "values ( :name, :rank, :city, :location, :idGroup)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", hotel.getHotelName());
        parameters.addValue("rank", hotel.getRank());
        parameters.addValue("city", hotel.getCity());
        parameters.addValue("location", hotel.getLocation());
        parameters.addValue("idGroup", hotel.getIdGroup());
        jdbc.update(sql, parameters);

        sql = "select * from hotel where hotelName = :name and `rank` = :rank and city = :city " +
                "and location = :location and idGroup = :idGroup LIMIT 1";
        return jdbc.queryForObject(sql, parameters, new HotelMapper());
    }

    @Override
        public List<Hotel> getHotelList() {
        String sql = "Select * from hotel";
        SqlParameterSource parameterSource = new MapSqlParameterSource();
        return jdbc.query(sql, parameterSource, new HotelMapper());
    }

    @Override
    @Transactional
    public Hotel getHotelById(int id) {
        String sql = "Select * from hotel where idHotel = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        return jdbc.queryForObject(sql, parameters, new  HotelMapper());
    }

    @Override
    public List<EstablishmentDto> getAllEstablishList() {
        String sql = "select e.idEstablishment, e.estName, e.estDescription, e.idHotel, t.estTypeName " +
                "from establishment e inner join establish_type t on e.idType = t.idEstablishType;";
        SqlParameterSource parameterSource = new MapSqlParameterSource();
        return jdbc.query(sql, parameterSource, new EstablishmentDtoMapper());
    }

    @Override
    public List<EstablishmentDto> getEstablishListByHotelId(int idHotel) {
        String sql = "select e.idEstablishment, e.estName, e.estDescription, e.idHotel, t.estTypeName" +
                " from establishment e inner join establish_type t on e.idType = t.idEstablishType " +
                "where e.idHotel = :idHotel;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idHotel", idHotel);
        return jdbc.query(sql, parameterSource, new EstablishmentDtoMapper());
    }

    @Override
    @Transactional
    public void addEstablishmentToHotel(EstablishmentDto establishment) {
        String sql = "Select * from establish_type where estTypeName = :type";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("type", establishment.getType());
        EstType type = jdbc.queryForObject(sql, parameterSource, new EstTypeMapper());

        sql = "Insert into establishment(estName, estDescription, idHotel, idType) " +
                "values (:estName, :estDescription, :idHotel, :idType)";
        parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idType", type.getIdEstType());
        parameterSource.addValue("idHotel", establishment.getIdHotel());
        parameterSource.addValue("estName", establishment.getName());
        parameterSource.addValue("estDescription", establishment.getDescription());
        jdbc.update(sql, parameterSource);
    }

    @Override
    public EstablishmentDto getEstablishmentById(EstablishmentDto establishment) {
        String sql = "select e.idEstablishment, e.estName, e.estDescription, e.idHotel, t.estTypeName" +
                " from establishment e inner join establish_type t on e.idType = t.idEstablishType " +
                "where e.idHotel = :idHotel Limit 1";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idEstablishment", establishment.getIdEstablishment());
        return jdbc.queryForObject(sql, parameterSource, new EstablishmentDtoMapper());
    }

    @Override
    @Transactional
    public void updateEstablishment(EstablishmentDto establishment) {
        String sql = "Update establishment set estName = :name, " +
                "estDescription = :description where idEstablishment = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", establishment.getName());
        parameterSource.addValue("description", establishment.getDescription());
        parameterSource.addValue("id", establishment.getIdEstablishment());
        jdbc.update("sql", parameterSource);
    }

    @Override
    @Transactional
    public void deleteEstablishment(EstablishmentDto establishment) {
        String sql = "Delete from establishment where idEstablishment = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", establishment.getIdEstablishment());
        jdbc.update(sql, parameterSource);
    }

    @Override
    public List<Facility> getFacilitiesByHotelId(int idHotel) {
        String sql = "Select * from facility where idHotel = :idHotel";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idHotel", idHotel);
        return jdbc.query(sql, parameterSource, new FacilityMapper());
    }

    @Override
    @Transactional
    public void addFacilityToHotel(Facility facility) {
        String sql = "Insert into facility(facilityName, facilityDescription, idHotel) " +
                " values (:name, :description, :idHotel)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idHotel", facility.getIdHotel());
        parameterSource.addValue("name", facility.getFacilityName());
        parameterSource.addValue("description", facility.getFacilityDescription());
        jdbc.update(sql, parameterSource);
    }

    @Override
    public Facility getFacilityById(int idFacility) {
        String sql = "Select * from facility where idFacility = :id LIMIT 1";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", idFacility);
        return jdbc.queryForObject(sql, parameterSource, new FacilityMapper());
    }

    @Override
    @Transactional
    public void updateFacility(Facility facility) {
        String sql = "UPDATE facility set facilityName = :name, facilityDescription = :description" +
                " where idFacility = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", facility.getIdFacility());
        parameterSource.addValue("name", facility.getFacilityName());
        parameterSource.addValue("description", facility.getFacilityDescription());
        jdbc.update(sql, parameterSource);
    }

    @Override
    @Transactional
    public void deleteFacility(Facility facility) {
        String sql = "DELETE from facility where idFacility = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", facility.getIdFacility());
        jdbc.update(sql,parameterSource);
    }
}
