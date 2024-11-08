package payment.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import payment.service.common.UrlConstants;
import payment.service.request.PaymentsRequest;
import payment.service.response.PaymentsResponse;
import payment.service.response.Response;
import payment.service.service.PaymentsService;

@RestController
@RequestMapping(UrlConstants.BASE_API)
public class PaymentsController {

	@Autowired
	private PaymentsService paymentsService;
	
	// ================== Create Payment ==============
	
	@PostMapping(UrlConstants.CREATE_PAYMENT)
	public ResponseEntity<Response> createPayment(@RequestBody() PaymentsRequest paymentsRequest){
		Response response = null;
		PaymentsResponse paymentsResponse = null;
		
		paymentsResponse = paymentsService.createPayment(paymentsRequest);
		if(paymentsResponse != null) {
			response = new Response(paymentsResponse, "Payment Initiated Successfully", HttpStatus.CREATED);
		}else {
			response = new Response(paymentsResponse, "Payment Not Initiated", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	// ================== Get Payment Details By Order Id ==================
	
	@GetMapping(UrlConstants.GET_PAYMENT_BY_ORDERID)
	public ResponseEntity<Response> GetPaymentDetailsByOrderId(@RequestParam(required = true) Long orderId){
		Response response = null;
		PaymentsResponse paymentsResponse = null;
		
		paymentsResponse = paymentsService.GetPaymentDetailsByOrderId(orderId);
		if(paymentsResponse != null) {
			response = new Response(paymentsResponse, "Payment Successful And Your Order Confirmed", HttpStatus.OK);
		}else {
			response = new Response(paymentsResponse, "Payment Details Not Fetched", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
}
