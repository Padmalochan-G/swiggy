package resturant.service.common;

import java.util.List;

public class Common {

	public static boolean isNotNull(Long id) {
		if(id != null && id > 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotNull(List<?> list) {
		if(list != null && !list.isEmpty()) {
			return true;
		}
		return false;
	}
}
