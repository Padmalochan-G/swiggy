package resturant.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import resturant.service.common.Common;
import resturant.service.entity.Menu;
import resturant.service.repository.MenuRepository;
import resturant.service.request.MenuRequest;
import resturant.service.response.MenuResponse;
import resturant.service.service.MenuService;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService{

	private MenuRepository menuRepository;
	
	// ============================= Add Menu ===============================
	
	@Override
	public MenuResponse addMenu(MenuRequest menuRequest) {
		MenuResponse menuResponse = null;
		Menu menu = null;
		
		if(menuRequest != null) {
			menu = new Menu();
			BeanUtils.copyProperties(menuRequest, menu);
		}
		if(menu != null) {
			menuResponse = new MenuResponse();
			menu = menuRepository.save(menu);
			BeanUtils.copyProperties(menu, menuResponse);
		}
		
		return menuResponse;
	}

	// =========================== Get All Menu =============================
	
	@Override
	public List<MenuResponse> getAllMenu(Long resturantId) {
		List<MenuResponse> menuResponseList = null;
		MenuResponse menuResponse = null;
		List<Menu> menu = null;
		
		menu = menuRepository.getAllMenu(resturantId);
		if(Common.isNotNull(menu)) {
			menuResponseList = new ArrayList<MenuResponse>();
			for(Menu menus : menu) {
				menuResponse = new MenuResponse();
				BeanUtils.copyProperties(menus, menuResponse);
				menuResponseList.add(menuResponse);
			}
		}
		return menuResponseList;
	}

	// =========================== Get Menu By Id =============================
	
	@Override
	public MenuResponse getMenuById(Long menuId) {
		MenuResponse menuResponse = null;
		Menu menu = null;
		
		menu = menuRepository.findById(menuId).orElse(null);
		if(menu != null) {
			menuResponse = new MenuResponse();
			BeanUtils.copyProperties(menu, menuResponse);
		}
		
		return menuResponse;
	}

}
