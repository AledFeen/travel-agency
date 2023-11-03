package bondarenko.travelagency.repositories;

import bondarenko.travelagency.models.Route;

import java.util.List;

public interface RouteRepository {
    void addRoute(Route route);
    List<Route> getRouteList();
    Route getRouteById(int id);
    void updateRoute(Route route);

    //!!!!delete route
}
