package com.driver.repository;

import com.driver.model.Driver;
import com.driver.model.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.driver.model.Customer;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer>{


}
