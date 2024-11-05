package payment.service.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderResponse implements Serializable{

	private static final long serialVersionUID = -2424591546816831537L;

	private Long id;
	
	private Long userId;

	private Long restaurantId;

	private LocalDateTime orderTime;

	private String orderStatus; //'PENDING', 'COMPLETED'

	private double totalAmount;

	private List<OrderItemResponse> items;
}
