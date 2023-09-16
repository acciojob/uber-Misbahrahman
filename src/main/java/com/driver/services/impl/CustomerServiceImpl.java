package com.driver.services.impl;

import com.driver.exception.CustomException;
import com.driver.model.Driver;
import com.driver.model.TripBooking;
import com.driver.model.TripStatus;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		customerRepository2.deleteById(customerId);
	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		List<Driver> list = driverRepository2.findAll();
		Collections.sort(list , (a , b) -> {return (a.getDriverId() - b.getDriverId());});
		Driver driver = null;
		for(Driver x : list){
			if(x.getCab().isAvailable()){
				driver = x;
				break;
			}
		}
		Optional<Customer> response = customerRepository2.findById(customerId);
		Customer customer = response.get();
		if(driver == null)throw new CustomException("No cab available!");
		TripBooking trip = TripBooking.builder()
				.customer(customer)
				.tripStatus(TripStatus.CONFIRMED)
				.distanceInKm(distanceInKm)
				.fromlocation(fromLocation)
				.toLocation(toLocation)
				.driver(driver)
				.build();

		TripBooking savedTrip =  tripBookingRepository2.save(trip);
		customer.getTripBookingList().add(savedTrip);
		driver.getTripBookingList().add(savedTrip);

		customerRepository2.save(customer);
		driverRepository2.save(driver);

		return savedTrip;

	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> response = tripBookingRepository2.findById(tripId);
		TripBooking trip = response.get();
		trip.setTripStatus(TripStatus.CANCELED);
		trip.getDriver().getCab().setAvailable(true);

	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> response = tripBookingRepository2.findById(tripId);
		TripBooking trip = response.get();
		trip.setTripStatus(TripStatus.COMPLETED);
		trip.getDriver().getCab().setAvailable(true);


	}
}
