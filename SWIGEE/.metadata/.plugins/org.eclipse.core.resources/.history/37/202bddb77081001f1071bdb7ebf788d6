package order.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import order.service.common.UrlConstants;
import order.service.request.OrderRequest;
import order.service.response.OrderResponse;
import order.service.response.Response;
import order.service.response.RestaurantResponse;
import order.service.response.UserResponse;
import order.service.service.OrderService;

@RestController
@RequestMapping(UrlConstants.BASE_API)
@AllArgsConstructor
public class OrderController {

	private OrderService orderService;
	
	// ==================== Create An Order ==================
	
	@PostMapping(UrlConstants.ORDER_CREATE)
	public ResponseEntity<Response> createOrder(@RequestBody() OrderRequest orderRequest){
		Response response = null;
		OrderResponse orderResponse = null;
		
		orderResponse = orderService.createOrder(orderRequest);
		if(orderResponse != null) {
			response = new Response(orderResponse, "Order Placed Successfully", HttpStatus.CREATED);
		}else {
			response = new Response(orderResponse, "Unable TO Placed Order", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	// ==================== Get Order By Id ==================
	
	@GetMapping(UrlConstants.GET_ORDER_BY_ID)
	public ResponseEntity<Response> getOrderById(@RequestParam(required = true) Long id){
		Response response = null;
		OrderResponse orderResponse = null;
		
		orderResponse = orderService.getOrderById(id);
		if(orderResponse != null) {
			response = new Response(orderResponse, "Order Fetched Successfully", HttpStatus.OK);
		}else {
			response = new Response(orderResponse, "Unable TO Fetch Order", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	// ==================== Get Employee By emp_id Associate With Address_Id ==================
	
	@GetMapping(UrlConstants.GET_USER_DETAILS)
	public ResponseEntity<Response> getUserDetails(@RequestParam(required = true) Long id){
		Response response = null;
		UserResponse userResponse = null;
		
		userResponse = orderService.getUserDetails(id);
		if(userResponse != null) {
			response = new Response(userResponse, "User Details Fetched Successfully", HttpStatus.OK);
		}else {
			response = new Response(userResponse, "Unable TO Fetch User Details", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	// ==================== Get Employee By emp_id Associate With Address_Id ==================
	
	@GetMapping(UrlConstants.GET_RESTURENT_DETAILS)
	public ResponseEntity<Response> getRestudentDetails(@RequestParam(required = true) Long id){
		Response response = null;
		RestaurantResponse restuResponse = null;
		
		restuResponse = orderService.getRestudentDetails(id);
		if(restuResponse != null) {
			response = new Response(restuResponse, "Restaurent Details Fetched Successfully", HttpStatus.OK);
		}else {
			response = new Response(restuResponse, "Unable TO Fetch REstaurent Details", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
}
