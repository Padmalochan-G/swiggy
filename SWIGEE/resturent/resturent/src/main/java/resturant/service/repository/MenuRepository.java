package resturant.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import resturant.service.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long>{

	@Query("SELECT m FROM Menu m WHERE m.restaurantId = :resturantId")
	public List<Menu> getAllMenu(@Param("resturantId") Long resturantId);

}
