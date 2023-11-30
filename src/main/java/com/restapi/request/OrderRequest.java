package com.restapi.request;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotEmpty(message = "User Id should not be empty")
    @Positive
    private Long userId;

    @Positive
    @NotEmpty(message = "Room Id should not be empty")
    private Long roomId;

    @NotEmpty(message = "First Name should not be empty")
    private String firstName;

    @NotEmpty(message = "Last Name should not be empty")
    private String lastName;

    @NotEmpty(message = "Last Name should not be empty")
    private String gender;

    @NotEmpty(message = "Address should not be empty")
    private String address1;

    private String address2;

    @NotEmpty(message = "City should not be empty")
    private String city;

    @NotEmpty(message = "State should not be empty")
    private String state;

    @NotEmpty(message = "ZipCode should not be empty and alphabets and special characters")
    @Positive
    private int zipCode;

    @NotEmpty(message = "Phone Number should not be empty and alphabets and special characters")
    @Positive
    private Long phoneNumber;

    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotEmpty(message = "Check In Date should not be empty")
    private LocalDate checkInDate;

    @NotEmpty(message = "Check Out Date should not be empty")
    private LocalDate checkOutDate;

    private LocalTime checkInTime;

    private LocalTime checkOutTime;

    @NotEmpty(message = "Adults should not be empty and alphabets and special characters")
    @Positive
    private int adults;

    @NotEmpty(message = "Children should not be empty and alphabets and special characters")
    @Positive
    private int children;
}
