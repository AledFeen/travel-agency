package bondarenko.travelagency.models;

import lombok.Data;

@Data
public class Review { //dto
    int idReview;
    int idRoute;
    String login;
    int rank;
    String firstName;
    String secondName;
    String description;
}
