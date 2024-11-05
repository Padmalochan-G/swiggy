package payment.service.response;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentsResponse implements Serializable{

	private static final long serialVersionUID = -6110803368228781011L;

	private Long id;
	
	private Long orderId;
	
	private BigDecimal amount;
	
	private String status;
	
	private String modeOfPayment;
	
	private String transactionId;
}
