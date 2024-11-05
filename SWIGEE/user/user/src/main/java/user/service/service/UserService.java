package user.service.service;

import user.service.request.UserRequest;
import user.service.response.UserResponse;

public interface UserService {

	public UserResponse createUser(UserRequest userRequest);

	public UserResponse getUserById(Long id);

	public Boolean ifUserExitOrNot(String email, String phone);

	public UserResponse getUserByEmailId(String emailId, String password);

}
