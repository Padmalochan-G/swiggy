package user.service.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.service.entity.User;
import user.service.repository.UserRepository;
import user.service.request.UserRequest;
import user.service.response.UserResponse;
import user.service.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	// ===================== Save User ====================
	
	@Override
	public UserResponse createUser(UserRequest userRequest) {

		UserResponse userResponse = null;
		User user = new User();
		if(userRequest != null) {
			BeanUtils.copyProperties(userRequest, user);
		}
		if(user != null) {
			userResponse = new UserResponse();
			user = userRepository.save(user);
			BeanUtils.copyProperties(user, userResponse);
		}
		
		return userResponse;
	}

	// ================ Get User =================
	
	@Override
	public UserResponse getUserById(Long id) {

		UserResponse userResponse = null;
		User user = userRepository.findById(id).orElse(null);
		if(user != null) {
			userResponse = new UserResponse();
			BeanUtils.copyProperties(user, userResponse);
		}
		
		return userResponse;
	}

	// ============== Is User Exit ============
	
	@Override
	public Boolean ifUserExitOrNot(String email, String phone) {
		Boolean isExist = false;

		User user = userRepository.findByEmailPhone(email, phone);
		if(user != null) {
			isExist = true;
		}
		
		return isExist;
	}

	// ================ Get User By Email Id And Password =================
	
	@Override
	public UserResponse getUserByEmailId(String emailId, String password) {

		UserResponse userResponse = null;
		User user = userRepository.findByEmailIdPassword(emailId, password);
		if(user != null) {
			userResponse = new UserResponse();
			BeanUtils.copyProperties(user, userResponse);
		}
		return userResponse;
	}
	
	// ======================================================================
	
}
