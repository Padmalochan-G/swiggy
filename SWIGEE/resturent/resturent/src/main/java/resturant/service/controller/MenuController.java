package resturant.service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import resturant.service.common.Common;
import resturant.service.common.UrlConstants;
import resturant.service.request.MenuRequest;
import resturant.service.response.MenuResponse;
import resturant.service.response.Response;
import resturant.service.service.MenuService;

@RequestMapping(UrlConstants.Menu.BASE_API)
@AllArgsConstructor
@RestController
public class MenuController {

	private MenuService menuService;

	// ============================= Add Menu ===============================

	@PostMapping(UrlConstants.Menu.SAVE_MENU)
	public ResponseEntity<Response> addMenu(@RequestBody() MenuRequest menuRequest) {
		Response response = null;
		MenuResponse menuResponse = null;

		menuResponse = menuService.addMenu(menuRequest);
		if (Common.isNotNull(menuRequest.getId())) {
			if (menuResponse != null) {
				response = new Response(menuResponse, "Menu Update Successfully", HttpStatus.CREATED);
			} else {
				response = new Response(menuResponse, "Not Able To Update Menu", HttpStatus.NO_CONTENT);
			}
		} else {
			if (menuResponse != null) {
				response = new Response(menuResponse, "Menu Added Successfully", HttpStatus.CREATED);
			} else {
				response = new Response(menuResponse, "Not Able To Add Menu", HttpStatus.NO_CONTENT);
			}
		}

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	// =========================== Get All Menu =============================
	
	@GetMapping(UrlConstants.Menu.GET_ALL_MENU)
	public ResponseEntity<Response> getAllMenu(@RequestParam(required = true) Long resturantId) {
		Response response = null;
		List<MenuResponse> menuResponse = null;
		
		menuResponse = menuService.getAllMenu(resturantId);
		if(Common.isNotNull(menuResponse)) {
			response = new Response(menuResponse, "Menu List Fetched Successfully", HttpStatus.OK);
		}else {
			response = new Response(menuResponse, "Not Able To Fetch Menu List", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	// =========================== Get Menu By Id =============================
	
	@GetMapping(UrlConstants.Menu.GET_BY_ID)
	public ResponseEntity<Response> getMenuById(@RequestParam(required = true) Long menuId) {
		Response response = null;
		MenuResponse menuResponse = null;
		
		menuResponse = menuService.getMenuById(menuId);
		if(menuResponse != null) {
			response = new Response(menuResponse, "Menu Fetched Successfully", HttpStatus.OK);
		}else {
			response = new Response(menuResponse, "Not Able To Fetched Menu", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
