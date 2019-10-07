package pl.mukova.cars.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private long id;
    private String brand;
    private String model;
    private Color color;

}
