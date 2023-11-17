package com.restapi.request;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryRequest {
    @NotEmpty
    @Positive(message = "Id should contain only number")
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Positive(message = "Id should contain only number")
    private Long amenityId;
}
