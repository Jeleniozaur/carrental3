package com.jeleniozaur.carrental.car.repository;

import com.jeleniozaur.carrental.car.model.Car;
import com.jeleniozaur.carrental.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
public interface CarRepository extends JpaRepository<Car, Long> {
    public List<Car> findByRented(boolean rented);
}
