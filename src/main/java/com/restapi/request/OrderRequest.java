package com.restapi.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {

    @NotEmpty(message = "Field should not be empty")
    @Positive
    private Long userId;

    @Positive
    @NotEmpty(message = "Field should not be empty")
    private Long roomId;
}
