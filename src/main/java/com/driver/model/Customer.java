package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

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

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int customerId;

    String mobile;

    String password;

    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
    List<TripBooking> tripBookingList = new ArrayList<>();


}