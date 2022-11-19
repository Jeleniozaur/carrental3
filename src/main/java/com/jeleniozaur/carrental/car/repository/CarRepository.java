package com.jeleniozaur.carrental.car.repository;

import com.jeleniozaur.carrental.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByRented(boolean rented);
    List<Car> findByRentedToUserId(Long userId);
    
}
