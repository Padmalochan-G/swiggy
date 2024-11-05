package resturant.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import resturant.service.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

	@Query("SELECT r FROM Restaurant r WHERE r.name = :name AND r.location = :location AND r.ownerId = :ownerId")
	public Restaurant isPresent(@Param("name") String name,@Param("location") String location,@Param("ownerId") Long ownerId);

}
