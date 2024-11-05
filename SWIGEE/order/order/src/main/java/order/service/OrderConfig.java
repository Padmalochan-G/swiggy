package order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import order.service.response.Response;

//@FeignClient(name = "ORDER-SERVICE",url = "http://localhost:9091/restaurant")
@FeignClient(name = "RESTURENT-SERVICE", path = "/restaurant")
public interface OrderConfig {
	
	@GetMapping("/get/by/id")
	public ResponseEntity<Response> getRestaurantById(@RequestParam(required = true) Long id);
}
