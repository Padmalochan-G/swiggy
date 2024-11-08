package payment.service.serviceImpl;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import payment.service.OrderConfig;
import payment.service.entity.Payments;
import payment.service.repository.PaymentsRepository;
import payment.service.request.OrderRequest;
import payment.service.request.PaymentsRequest;
import payment.service.response.OrderResponse;
import payment.service.response.PaymentsResponse;
import payment.service.response.Response;
import payment.service.service.PaymentsService;

@Service
public class PaymentsServiceImpl implements PaymentsService{

	@Autowired
	private PaymentsRepository paymentsRepository;
	
	@Autowired
	private OrderConfig orderConfig;
	
	// =================== Create Payment ==================
	
	@Override
	public PaymentsResponse createPayment(PaymentsRequest paymentsRequest) {
		PaymentsResponse paymentsResponse = null;
		Payments payments = null;
		
		if(paymentsRequest != null) {
			payments = new Payments();
			String transactionId = generateTransactionId();
			BeanUtils.copyProperties(paymentsRequest, payments);
			payments.setTransactionId(transactionId);
		}
		
		if(payments != null) {
			paymentsResponse = new PaymentsResponse();
			payments = paymentsRepository.save(payments);
			BeanUtils.copyProperties(payments, paymentsResponse);
		}
		
		return paymentsResponse;
	}

	// ================== Generate Transaction Id ====================
	
	private String generateTransactionId() {
		String transactionId = null;
		String generatedId = null;
		
		transactionId = paymentsRepository.getTransactionId();
		if(transactionId != null && transactionId.trim().length() > 0) {
			int value = Integer.parseInt(transactionId.substring(5));
			generatedId = "ORD_"+value+1;
		}else {
			generatedId = "ORD_"+1;
		}
		return generatedId;
	}
	
	// ================== Get Payment Details By Order Id ==============
	
	@Override
	public PaymentsResponse GetPaymentDetailsByOrderId(Long orderId) {
		PaymentsResponse paymentsResponse = null;
		Payments payments = null;
		OrderRequest orderRequest = null;
		OrderResponse orderResponse = null;
		Response response = null;
		Response updateResponse = null;
		
		payments = paymentsRepository.GetPaymentDetailsByOrderId(orderId);
		if(payments != null) {
			paymentsResponse = new PaymentsResponse();
			BeanUtils.copyProperties(payments, paymentsResponse);
			
			if(payments.getStatus().equalsIgnoreCase("completed")) {
				response = orderConfig.getOrderById(orderId).getBody();
				if(response != null && response.getResponse() instanceof Map) {
					Map<String, Object> responseMap = (Map<String, Object>)response.getResponse();
					orderRequest = new OrderRequest();
					orderRequest.setId(((Integer)responseMap.get("id")).longValue());
					orderRequest.setOrderStatus("Confirmed");
					orderRequest.setRestaurantId(((Integer)responseMap.get("restaurantId")).longValue());
					orderRequest.setTotalAmount((double)responseMap.get("totalAmount"));
					orderRequest.setUserId(((Integer)responseMap.get("userId")).longValue());
					
					updateResponse = orderConfig.createOrder(orderRequest).getBody();
				}
			}
		}
		return paymentsResponse;
	}

}
