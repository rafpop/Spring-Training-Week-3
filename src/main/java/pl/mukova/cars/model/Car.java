package pl.mukova.cars.model;

import lombok.*;
import org.springframework.hateoas.ResourceSupport;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car extends ResourceSupport {

    private long carId;
    private String brand;
    private String model;
    private Color color;

}
