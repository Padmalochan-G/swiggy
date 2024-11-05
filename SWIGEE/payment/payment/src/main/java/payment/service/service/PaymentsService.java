package payment.service.service;

import payment.service.request.PaymentsRequest;
import payment.service.response.PaymentsResponse;

public interface PaymentsService {

	public PaymentsResponse createPayment(PaymentsRequest paymentsRequest);

	public PaymentsResponse GetPaymentDetailsByOrderId(Long orderId);

}
