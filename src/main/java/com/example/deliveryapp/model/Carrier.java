package com.example.deliveryapp.model;

import com.example.deliveryapp.model.template.AbsLongEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "carrier")
public class Carrier extends AbsLongEntity {
    @Column(nullable = false, name = "full_name")
    private String fullName;

    @Column(nullable = false, name = "user_name")
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String password;

    @ManyToMany
    @JoinTable(name = "carrier_region",
            joinColumns = {@JoinColumn(name = "carrier_id")},
            inverseJoinColumns = {@JoinColumn(name = "region_id")})
    private List<Region> regions;
}