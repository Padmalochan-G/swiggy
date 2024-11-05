package order.service.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class RestaurantResponse implements Serializable{

	private static final long serialVersionUID = 4497419820500866331L;

	private Long id;

	private String name;

	private String location;

	private Double rating;

    private Long ownerId;
}
