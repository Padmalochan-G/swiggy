package resturant.service.service;

import java.util.List;

import resturant.service.request.RestaurantRequest;
import resturant.service.response.RestaurantResponse;

public interface RestaurantService {

	public RestaurantResponse createRestaurant(RestaurantRequest restaurant);

	public Boolean isRestaurantPresent(RestaurantRequest restaurant);

	public List<RestaurantResponse> getAllRestaurant();

	public RestaurantResponse getRestaurantById(Long id);

}
