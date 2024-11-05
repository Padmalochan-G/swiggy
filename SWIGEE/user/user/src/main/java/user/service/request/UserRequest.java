package user.service.request;

import lombok.Data;

@Data
public class UserRequest {

	private String name;

	private String email;

	private String password;

	private String role;

	private String address;

	private String phone;
}
