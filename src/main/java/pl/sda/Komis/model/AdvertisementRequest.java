package pl.sda.Komis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class AdvertisementRequest {

    @NotNull
    private Car car;

    @NotNull
    private Integer price;

    @NotNull
    private String description;

    @NotNull
    private boolean sold;
}
