package resturant.service.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class MenuRequest implements Serializable{

	private static final long serialVersionUID = 5883470230234840465L;

	private Long id;

    private Long restaurantId;

    private String itemName;

    private Double price;

    private String category;
}
