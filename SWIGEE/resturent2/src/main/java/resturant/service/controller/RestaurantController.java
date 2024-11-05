package resturant.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import resturant.service.common.Common;
import resturant.service.common.UrlConstants;
import resturant.service.request.RestaurantRequest;
import resturant.service.response.Response;
import resturant.service.response.RestaurantResponse;
import resturant.service.service.RestaurantService;

@RestController
@RequestMapping(UrlConstants.Restaurant.BASE_API)
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	// ================ Create Restaurant ================

	@PostMapping(UrlConstants.Restaurant.SAVE_RESTAURANT)
	public ResponseEntity<Response> createRestaurant(@RequestBody() RestaurantRequest restaurant) {
		Response response = null;
		RestaurantResponse restaurantResponse = null;
		Boolean isPresent = false;
		
		if(!Common.isNotNull(restaurant.getId())) {
			isPresent = restaurantService.isRestaurantPresent(restaurant);
			if(isPresent) {
				response = new Response(null, "This Restaurent Alreaddy Resistered ! Please Provide Valid Credentials", HttpStatus.OK);
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			}
		}

		restaurantResponse = restaurantService.createRestaurant(restaurant);

		if (Common.isNotNull(restaurant.getId())) {
			if (restaurantResponse != null) {
				response = new Response(restaurantResponse, "Restaurent Updated Successfully", HttpStatus.CREATED);
			} else {
				response = new Response(null, "Unable To Update Restaurant", HttpStatus.NO_CONTENT);
			}
		} else {
			if (restaurantResponse != null) {
				response = new Response(restaurantResponse, "Restaurent Saved Successfully", HttpStatus.CREATED);
			} else {
				response = new Response(null, "Unable To Save Restaurant", HttpStatus.NO_CONTENT);
			}
		}

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	// ======================== Get All Restaurant =======================
	
	@GetMapping(UrlConstants.Restaurant.GET_ALL_RESTAURANT)
	public ResponseEntity<Response> getAllRestaurant(){
		Response response = null;
		List<RestaurantResponse> restaurantResponses = null;
		
		restaurantResponses = restaurantService.getAllRestaurant();
		if(Common.isNotNull(restaurantResponses)) {
			response = new Response(restaurantResponses, "Restaurant List Fetched Successfully", HttpStatus.OK);
		}else {
			response = new Response(null, "Unable To Fetche Restaurant List", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	// ======================== Get Restaurant By Id =======================
	
	@GetMapping(UrlConstants.Restaurant.GET_RESTAURANT_BY_ID)
	public ResponseEntity<Response> getRestaurantById(@RequestParam(required = true) Long id){
		Response response = null;
		RestaurantResponse restaurantResponses = null;
		System.out.println("app 2");
		restaurantResponses = restaurantService.getRestaurantById(id);
		if(restaurantResponses != null) {
			response = new Response(restaurantResponses, "Restaurant Details Fetched Successfully", HttpStatus.OK);
		}else {
			response = new Response(null, "Unable To Fetch Restaurant Details", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	
}
