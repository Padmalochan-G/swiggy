package resturant.service.service;

import java.util.List;

import resturant.service.request.MenuRequest;
import resturant.service.response.MenuResponse;

public interface MenuService {

	public MenuResponse addMenu(MenuRequest menuRequest);

	public List<MenuResponse> getAllMenu(Long resturantId);

	public MenuResponse getMenuById(Long menuId);

}
