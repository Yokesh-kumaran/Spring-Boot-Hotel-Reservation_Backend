package com.restapi.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.model.Category;
import com.restapi.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {
    @Positive(message = "CategoryId should be a positive number")
    private Long id;

    @NotNull(message = "CategoryId should not be null")
    @Positive(message = "CategoryId should be a positive number")
    private Long categoryId;

    @NotNull(message = "Description should not be null")
    private String description;

    @NotNull(message = "Price should not be null")
    @Positive(message = "Price should be a positive number")
    private Double price;

    private byte[] photo;
}
