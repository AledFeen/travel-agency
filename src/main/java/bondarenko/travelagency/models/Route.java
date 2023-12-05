package bondarenko.travelagency.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    int IdRoute;
    String rName;
    String description;
    int peopleCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startData;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date endData;
    int minPrice;
    int maxPrice;
    int transportPrice;
    int isPublish;

}
