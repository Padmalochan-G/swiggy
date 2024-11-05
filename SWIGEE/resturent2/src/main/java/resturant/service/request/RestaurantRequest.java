package resturant.service.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class RestaurantRequest implements Serializable{

	private static final long serialVersionUID = 1795299037904850796L;

	private Long id;

	private String name;

	private String location;

	private Double rating;

	private Long ownerId;
}
