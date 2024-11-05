package resturant.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import resturant.service.common.Common;
import resturant.service.entity.Restaurant;
import resturant.service.repository.RestaurantRepository;
import resturant.service.request.RestaurantRequest;
import resturant.service.response.RestaurantResponse;
import resturant.service.service.RestaurantService;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private RestaurantRepository restaurantRepository;

	// ================ Create Restaurant ================
	
	@Override
	public RestaurantResponse createRestaurant(RestaurantRequest restaurant) {
		RestaurantResponse restaurantResponse = null;
		Restaurant restaurantBody = null;
		
		if(restaurant != null) {
			restaurantBody = new Restaurant();
			BeanUtils.copyProperties(restaurant, restaurantBody);
		}
		if(restaurantBody != null) {
			restaurantResponse = new RestaurantResponse();
			restaurantBody = restaurantRepository.save(restaurantBody);
			BeanUtils.copyProperties(restaurantBody, restaurantResponse);
		}
		return restaurantResponse;
	}

	// ================== Check Restaurant Present Or Not ===================
	@Override
	public Boolean isRestaurantPresent(RestaurantRequest restaurant) {
		Boolean isPresent = false;
		Restaurant restaurantBody = null;

		restaurantBody = restaurantRepository.isPresent(restaurant.getName(), restaurant.getLocation(), restaurant.getOwnerId());
		if(restaurantBody != null) {
			isPresent = true;
		}
				
		return isPresent;
	}
	
	// ======================== Get All Restaurant =======================

	@Override
	public List<RestaurantResponse> getAllRestaurant() {
		List<RestaurantResponse> restaurantResponsesList = null;
		RestaurantResponse restaurantResponse = null;
		List<Restaurant> restaurants = null;
		
		restaurants = restaurantRepository.findAll();
		if(Common.isNotNull(restaurants)) {
			restaurantResponsesList = new ArrayList<RestaurantResponse>();
			for(Restaurant restaurant : restaurants) {
				restaurantResponse = new RestaurantResponse();
				BeanUtils.copyProperties(restaurant, restaurantResponse);
				restaurantResponsesList.add(restaurantResponse);
			}
		}
		
		return restaurantResponsesList;
	}

	// ======================== Get Restaurant By Id =======================
	
	@Override
	public RestaurantResponse getRestaurantById(Long id) {
		RestaurantResponse restaurantResponse = null;
		Restaurant restaurant = null;
		
		restaurant = restaurantRepository.findById(id).orElse(null);
		if(restaurant != null) {
			restaurantResponse = new RestaurantResponse();
			BeanUtils.copyProperties(restaurant, restaurantResponse);
		}
		return restaurantResponse;
	}

}
