package payment.service.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderItemResponse implements Serializable{

	private static final long serialVersionUID = 8237011241500806567L;

	private Long id;
	
	private String itemName;

	private double itemPrice;

	private Long menuItemId;

	private int quantity;

	private Long orderId;
}
