package order.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "item_name")
    private String itemName;
	
	@Column(name = "item_price")
    private double itemPrice;
	
	@Column(name = "menu_item_id")
	private Long menuItemId;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "order_id")
    private Long orderId;

}
