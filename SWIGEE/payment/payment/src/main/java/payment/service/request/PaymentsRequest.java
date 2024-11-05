package payment.service.request;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentsRequest implements Serializable{

	private static final long serialVersionUID = 6126130629918888054L;

	private Long orderId;
	
	private BigDecimal amount;
	
	private String status;
	
	private String modeOfPayment;
	
}
