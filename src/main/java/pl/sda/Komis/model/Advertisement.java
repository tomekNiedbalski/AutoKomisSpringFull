package pl.sda.Komis.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Setter
public class Advertisement {

    @Id
    private String id;

    @NotNull
    private Car car;

    @NotNull
    private Integer price;

    @NotNull
    private String userName;

    @NotNull
    private String description;

    @NotNull
    private boolean sold;

}