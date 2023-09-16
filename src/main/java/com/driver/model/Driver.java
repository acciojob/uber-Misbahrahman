package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int driverId;

    String mobile;

    String password;

    @OneToOne(mappedBy = "driver" , cascade = CascadeType.ALL)
    Cab cab;

    @OneToMany(mappedBy = "driver")
    List<TripBooking> tripBookingList = new ArrayList<>();


}