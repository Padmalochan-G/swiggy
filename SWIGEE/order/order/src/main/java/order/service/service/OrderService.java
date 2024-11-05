package order.service.service;

import order.service.request.OrderRequest;
import order.service.response.OrderResponse;
import order.service.response.RestaurantResponse;
import order.service.response.UserResponse;

public interface OrderService {

	public OrderResponse createOrder(OrderRequest orderRequest);

	public OrderResponse getOrderById(Long id);

	public UserResponse getUserDetails(Long id);

	public RestaurantResponse getRestudentDetails(Long id);

}
