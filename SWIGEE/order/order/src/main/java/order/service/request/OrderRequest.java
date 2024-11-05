package order.service.request;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class OrderRequest implements Serializable{

	private static final long serialVersionUID = -7602320640603796022L;

	private Long id;
	
	private Long userId;

	private Long restaurantId;

	private String orderStatus; //'PENDING', 'COMPLETED'

	private double totalAmount;

	private List<OrderItemRequest> items;
}
