package resturant.service.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class MenuResponse implements Serializable{

	private static final long serialVersionUID = 887638075790455373L;

	private Long id;

    private Long restaurantId;

    private String itemName;

    private Double price;

    private String category;
}
