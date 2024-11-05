package order.service.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id")
    private Long userId;
	
	@Column(name = "restaurant_id")
    private Long restaurantId;
	
	@Column(name = "created_at")
    private LocalDateTime orderTime;
	
	@Column(name = "STATUS")
    private String orderStatus; // e.g., 'PENDING', 'COMPLETED'
	
	@Column(name = "total_amount")
    private double totalAmount;
	
    @OneToMany(mappedBy = "orderId")
    private List<OrderItem> items;

}
