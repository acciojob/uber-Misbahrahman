package com.driver.services.impl;

import com.driver.model.Cab;
import com.driver.model.Driver;
import com.driver.repository.CabRepository;
import com.driver.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.DriverRepository;

import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepository driverRepository3;

	@Autowired
	CabRepository cabRepository3;

	@Override
	public void register(String mobile, String password){
		//Save a driver in the database having given details and a cab with ratePerKm as 10 and availability as True by default.
		Cab cab = Cab.builder()
				.available(true)
				.perKmRate(10)
				.build();

		Driver driver = Driver.builder()
				.mobile(mobile)
				.password(password)
				.cab(cab)
				.build();

		driverRepository3.save(driver);

	}

	@Override
	public void removeDriver(int driverId){

		driverRepository3.deleteById(driverId);


	}

	@Override
	public void updateStatus(int driverId){
		//Set the status of respective car to unavailable
		Optional<Driver> response = driverRepository3.findById(driverId);
		Cab cab = response.get().getCab();
		cab.setAvailable(false);
		driverRepository3.save(response.get());


	}
}
