package com.example.deliveryapp.model;

import com.example.deliveryapp.model.template.AbsLongEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "region")
public class Region extends AbsLongEntity {

    @Column(nullable = false, name = "name")
    private String name;
}