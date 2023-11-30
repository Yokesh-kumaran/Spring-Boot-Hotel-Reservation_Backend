package com.restapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {
    private Long id;
    private String categoryName;
    private String description;
    private Double price;
    private String photo;
    private int bedCount;
    private boolean powerBackup;
    private boolean ac;
    private boolean tv;
    private boolean wifi;
    private boolean breakfast;
    private boolean lunch;
    private boolean dinner;
    private boolean parkingFacility;
    private boolean cctvCameras;
}
