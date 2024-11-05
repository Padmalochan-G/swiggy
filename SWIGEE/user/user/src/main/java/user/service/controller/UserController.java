package user.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import user.service.common.UrlConstants;
import user.service.request.UserRequest;
import user.service.response.Response;
import user.service.response.UserResponse;
import user.service.service.UserService;

@RestController
@RequestMapping(UrlConstants.BASE_API)
public class UserController {

	@Autowired
	private UserService userService;

	// ================ Save User =================

	@PostMapping(UrlConstants.SAVE_USER)
	public ResponseEntity<Response> createUser(@RequestBody() UserRequest userRequest) {

		Response response = null;
		UserResponse userResponse = null;
		Boolean isExist = false;

		if (userRequest.getEmail() != null && userRequest.getPhone() != null) {
			isExist = userService.ifUserExitOrNot(userRequest.getEmail(), userRequest.getPhone());

			if (isExist) {
				response = new Response(null, "User Alreaddy Exist Please Provide Valid Credential", HttpStatus.NO_CONTENT);
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			}
		}

		userResponse = userService.createUser(userRequest);
		if (userResponse != null) {
			response = new Response(userResponse, "Usesr Created Succefully", HttpStatus.CREATED);
		} else {
			response = new Response(userResponse, "Usesr Not Able To Create", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}

	// ================ Get User By Id =================

	@GetMapping(UrlConstants.GET_USER_BY_ID)
	public ResponseEntity<Response> getUserById(@RequestParam(required = true) Long id) {

		Response response = null;
		UserResponse userResponse = userService.getUserById(id);
		if (userResponse != null) {
			response = new Response(userResponse, "User Fetched Successfully", HttpStatus.OK);
		} else {
			response = new Response(null, "Not Able To Fetch User", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	// ================ Get User By Email Id And Password =================

	@GetMapping(UrlConstants.GET_USER_BY_EMAIL_ID_PASSWORD)
	public ResponseEntity<Response> getUserByEmailId(@RequestParam(required = true) String emailId,
			@RequestParam(required = true) String password) {

		Response response = null;
		UserResponse userResponse = userService.getUserByEmailId(emailId, password);
		if (userResponse != null) {
			response = new Response(userResponse, "User Fetched Successfully", HttpStatus.OK);
		} else {
			response = new Response(null, "Please Provide Valid Credentials ! Email Id And Password Should Not Be Incorrect", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
