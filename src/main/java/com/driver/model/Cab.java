package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;

    int perKmRate;

    boolean available;


    @OneToOne
    @JoinColumn
    Driver driver;

}