package order.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import order.service.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	@Query("SELECT o FROM OrderItem o WHERE o.orderId = :id")
	public List<OrderItem> findIteams(@Param("id") Long id);

}
