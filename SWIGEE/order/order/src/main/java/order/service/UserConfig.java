package order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import order.service.response.Response;

@FeignClient(name = "user", url = "http://localhost:9090/user")
public interface UserConfig {

	@GetMapping("/get")
	public ResponseEntity<Response> getUserById(@RequestParam(required = true) Long id);
}
