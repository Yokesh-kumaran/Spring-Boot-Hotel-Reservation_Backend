package com.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "amenities")
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private int bedCount;

    @Column(nullable = false, length = 200)
    private boolean powerBackup;

    @Column(nullable = false, length = 200)
    private boolean ac;

    @Column(nullable = false, length = 200)
    private boolean tv;

    @Column(nullable = false, length = 200)
    private boolean wifi;

    @Column(nullable = false, length = 200)
    private boolean breakfast;

    @Column(nullable = false, length = 200)
    private boolean lunch;

    @Column(nullable = false, length = 200)
    private boolean dinner;

    @Column(nullable = false, length = 200)
    private boolean parkingFacility;

    @Column(nullable = false, length = 200)
    private boolean cctvCameras;

    @OneToMany(mappedBy = "amenity")
    private List<Category> categoryList = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
