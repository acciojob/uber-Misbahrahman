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

public class TripBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int tripBookingId;

    String fromlocation;

    String toLocation;

    int distanceInKm;

    TripStatus tripStatus;

    int bill;

    @ManyToOne
    @JoinColumn
    Driver driver;

    @ManyToOne
    @JoinColumn
    Customer customer;
}