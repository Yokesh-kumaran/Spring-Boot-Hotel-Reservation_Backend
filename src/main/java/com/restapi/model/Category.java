package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Room> roomList = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "amenity_id", referencedColumnName = "id")
    private Amenity amenity;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
