package com.example.deliveryapp.model;

import com.example.deliveryapp.model.template.AbsLongEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product extends AbsLongEntity {

    @Column(nullable = false, name = "name")
    private String name;
}