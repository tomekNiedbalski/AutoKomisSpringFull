package pl.sda.Komis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private String company;

    private String model;

    private Integer year;

    private Integer mileage;

}
