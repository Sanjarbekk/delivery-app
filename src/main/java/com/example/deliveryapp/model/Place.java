package com.example.deliveryapp.model;

import com.example.deliveryapp.model.template.AbsLongEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "place")
public class Place extends AbsLongEntity {

    @Column(nullable = false, name = "name")
    private String name;

    @JoinColumn(insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;
}