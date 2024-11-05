package order.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import order.service.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
