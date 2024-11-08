package order.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import order.service.OrderConfig;
import order.service.UserConfig;
import order.service.entity.Order;
import order.service.entity.OrderItem;
import order.service.repository.OrderItemRepository;
import order.service.repository.OrderRepository;
import order.service.request.OrderItemRequest;
import order.service.request.OrderRequest;
import order.service.response.OrderItemResponse;
import order.service.response.OrderResponse;
import order.service.response.Response;
import order.service.response.RestaurantResponse;
import order.service.response.UserResponse;
import order.service.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserConfig userConfig;
	
	@Autowired
	private OrderConfig orderConfig;
	
	@Autowired
	private WebClient webClient;
	
	// ======================== Create Order ==========================
	
	@Override
	public OrderResponse createOrder(OrderRequest orderRequest) {
		OrderResponse oredOrderResponse = null;
		OrderItemResponse orderItemResponse = null;
		List<OrderItemResponse> orderItemResponseList = null;
		OrderItem ordItem = null;
		List<OrderItem> ordItemList = null;
		Order order = null;
		
		if(orderRequest.getId() != null && orderRequest.getId()>0) {
			order = new Order();
			BeanUtils.copyProperties(orderRequest, order);
			order.setOrderTime(LocalDateTime.now());
			order = orderRepository.save(order);
			if(order != null) {
				oredOrderResponse = new OrderResponse();
				BeanUtils.copyProperties(order, oredOrderResponse);
			}
		}else {
		if(orderRequest != null) {
			order = new Order();
			BeanUtils.copyProperties(orderRequest, order);
			order.setOrderTime(LocalDateTime.now());
			order = orderRepository.save(order);
			if(order != null) {
				oredOrderResponse = new OrderResponse();
				BeanUtils.copyProperties(order, oredOrderResponse);
			}
			if(orderRequest.getItems() != null && !orderRequest.getItems().isEmpty()) {
				ordItemList = new ArrayList<OrderItem>();
				for(OrderItemRequest orderItem : orderRequest.getItems()) {
					ordItem = new OrderItem();
					BeanUtils.copyProperties(orderItem, ordItem);
					ordItem.setOrderId(order.getId());
					ordItemList.add(ordItem);
				}
			}
			if(ordItemList != null && !ordItemList.isEmpty()) {
				orderItemResponseList = new ArrayList<OrderItemResponse>();
				ordItemList = orderItemRepository.saveAll(ordItemList);
				for(OrderItem orders : ordItemList) {
					orderItemResponse = new OrderItemResponse();
					BeanUtils.copyProperties(orders, orderItemResponse);
					orderItemResponseList.add(orderItemResponse);
				}
			}
			if(orderItemResponseList != null && !orderItemResponseList.isEmpty()) {
				oredOrderResponse.setItems(orderItemResponseList);
			}
		}}
		return oredOrderResponse;
	}

	// ======================== Get Order By Id ==========================
	
	@Override
	public OrderResponse getOrderById(Long id) {
		OrderResponse orderResponse = null;
		List<OrderItemResponse> orderItemResponse = null;
		OrderItemResponse orderItemRes = null;
		Order order = null;
		List<OrderItem> orderItem = null;
		
		
		order = orderRepository.findById(id).orElse(null);
		if(order != null) {
			orderResponse = new OrderResponse();
			BeanUtils.copyProperties(order, orderResponse);
			
			orderItem = orderItemRepository.findIteams(id);
			if(orderItem != null && !orderItem.isEmpty()) {
				orderItemResponse = new ArrayList<OrderItemResponse>();
				for(OrderItem item : orderItem) {
					orderItemRes = new OrderItemResponse();
					BeanUtils.copyProperties(item, orderItemRes);
					orderItemResponse.add(orderItemRes);
				}
			}
			if(orderItemResponse != null && !orderItemResponse.isEmpty()) {
				orderResponse.setItems(orderItemResponse);
			}
		}
		
		return orderResponse;
	}

	// ======================== Get User Details By Address Associated Id =========================
	@Override
	public UserResponse getUserDetails(Long id) {
		UserResponse userResponse = null;
		Response response = null;
		
//		ResponseEntity<Response> micResponse= userConfig.getUserById(id);
//		if(micResponse != null)
//		response = micResponse.getBody();
		
//		response = userConfig.getUserById(id).getBody();
		
		response = webClient.get()
				.uri("http://localhost:9090/user/get?id={id}",id)
				.retrieve()
				.bodyToMono(Response.class)
				.block();
		
		if(response.getResponse() != null && response.getResponse() instanceof Map) {
			userResponse = new UserResponse();
			Map<String, Object> responseMap = (Map<String, Object>)response.getResponse();
			userResponse.setAddress((String)responseMap.get("address"));
			userResponse.setEmail((String)responseMap.get("email"));
			userResponse.setId(id);
			userResponse.setName((String)responseMap.get("name"));
			userResponse.setPhone((String)responseMap.get("phone"));
			userResponse.setRole((String)responseMap.get("role"));
			
		}
		return userResponse;
	}

	// ==================== Get Restaurant Details =================
	
	@Override
	public RestaurantResponse getRestudentDetails(Long id) {
		RestaurantResponse restaurantResponse = null;
		Response response = null;
		
		response = orderConfig.getRestaurantById(id).getBody();
		
//		response = webClient.get()
//				.uri("http://localhost:9091/restaurant/get/by/id?id={id}",id)
//				.retrieve()
//				.bodyToMono(Response.class)
//				.block();
		
		if(response.getResponse() != null && response.getResponse() instanceof Map) {
			restaurantResponse= new RestaurantResponse();
			Map<String, Object> responseMap = (Map<String, Object>)response.getResponse();
			
			restaurantResponse.setId(id);
			restaurantResponse.setLocation((String)responseMap.get("location"));
			restaurantResponse.setName((String)responseMap.get("name"));
			restaurantResponse.setOwnerId(((Integer)responseMap.get("ownerId")).longValue());
			restaurantResponse.setRating((Double)responseMap.get("rating"));
		}
		return restaurantResponse;
	}

}
