package user.service.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserResponse implements Serializable {

	private static final long serialVersionUID = 4672680927298976573L;

	private Long id;

	private String name;

	private String email;

	private String password;

	private String role;

	private String address;

	private String phone;
}
