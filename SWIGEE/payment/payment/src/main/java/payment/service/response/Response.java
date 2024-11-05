package payment.service.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {

	private Object response;
	
	private String message;
	
	private HttpStatus status;
}
