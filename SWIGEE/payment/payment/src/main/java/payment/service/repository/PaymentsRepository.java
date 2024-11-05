package payment.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import payment.service.entity.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Long>{

	@Query("SELECT p FROM Payments p WHERE p.orderId = :orderId")
	public Payments GetPaymentDetailsByOrderId(@Param("orderId") Long orderId);

	@Query("SELECT p.transactionId FROM Payments p ORDER BY p.id DESC LIMIT 1")
	public String getTransactionId();

}
