package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.FoodType;
import bondarenko.travelagency.models.HotelFoodType;
import bondarenko.travelagency.models.dto.FoodTypeDto;
import bondarenko.travelagency.models.mappers.FoodTypeDtoMapper;
import bondarenko.travelagency.models.mappers.FoodTypeMapper;
import bondarenko.travelagency.models.mappers.HotelFoodTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@AllArgsConstructor
public class FoodTypeRepositoryImpl implements FoodTypeRepository {
    private final NamedParameterJdbcTemplate jdbc;
    @Override
    public List<FoodTypeDto> getFoodTypeByHotelId(int idHotel) {
        String sql = "Select hft.id, hft.idHotel, hft.idFoodType, hft.price, ft.ftName, ft.ftDescription from hotel_foodtype hft inner join foodtype ft on hft.idFoodType = ft.idFoodType where hft.idHotel = :id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", idHotel);
        return jdbc.query(sql, source, new FoodTypeDtoMapper());
    }

    @Override
    public FoodTypeDto getHotelFoodTypeById(int idHft) {
        String sql = "Select hft.id, hft.idHotel, hft.idFoodType, hft.price, ft.ftName, ft.ftDescription from hotel_foodtype hft inner join foodtype ft on hft.idFoodType = ft.idFoodType where hft.id = :id LIMIT 1";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", idHft);
        return jdbc.queryForObject(sql, source, new FoodTypeDtoMapper());
    }

    @Override
    @Transactional
    public void addHotelFoodType(HotelFoodType hft) {
        String sql = "INSERT INTO hotel_foodtype(idHotel, idFoodType, price) VALUES (:idHotel, :idFoodType, :price)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("idHotel", hft.getIdHotel());
        source.addValue("idFoodType", hft.getIdFoodType());
        source.addValue("price", hft.getPrice());
        jdbc.update(sql, source);
    }

    @Override
    @Transactional
    public void deleteHotelFoodType(int idHft) {
        String sql = "Delete from hotel_foodtype where id = :id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", idHft);
        jdbc.update(sql, source);
    }

    @Override
    public List<FoodType> getFoodTypes() {
        String sql = "Select * from foodtype";
        return jdbc.query(sql, new FoodTypeMapper());
    }
}
