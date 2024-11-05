package payment.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import payment.service.request.OrderRequest;
import payment.service.response.Response;

@FeignClient(name = "ORDER-SERVICE", url = "http://localhost:9092/order")
public interface OrderConfig {

	@GetMapping("/get/by/id")
	public ResponseEntity<Response> getOrderById(@RequestParam(required = true) Long id);
	
	@PostMapping("/create")
	public ResponseEntity<Response> createOrder(@RequestBody() OrderRequest orderRequest);
}
