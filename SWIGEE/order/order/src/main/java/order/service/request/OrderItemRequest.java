package order.service.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderItemRequest implements Serializable{

	private static final long serialVersionUID = -3609119334018465964L;

	private String itemName;

	private double itemPrice;

	private Long menuItemId;

	private int quantity;
}
