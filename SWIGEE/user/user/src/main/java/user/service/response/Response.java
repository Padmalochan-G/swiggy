package user.service.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {

	private Object response;
	
	private String message;
	
	private HttpStatus status;
}
