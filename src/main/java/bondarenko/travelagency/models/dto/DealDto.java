package bondarenko.travelagency.models.dto;

import lombok.Data;

@Data
public class DealDto {
    int idDeal;
    int idRoute;
    String routeName;
    String userLogin;
    int totalPrice;
    int idStatus;
    String statusName;
    int idStartPlace;
    String city;
    String type;
    int phoneNumber;
    String email;
}
