package com.jeleniozaur.carrental.car.controller;

import com.jeleniozaur.carrental.car.model.Car;
import com.jeleniozaur.carrental.car.repository.CarRepository;
import com.jeleniozaur.carrental.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/car/")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("all")
    public ResponseEntity<List<Car>> getAllCars() {
        try {
            List<Car> cars = carService.getAllCars();
            if(!cars.isEmpty())
                return new ResponseEntity<>(cars, HttpStatus.OK);
            else
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("available")
    public ResponseEntity<List<Car>> getAvailableCars() {
        try {
            List<Car> cars = carService.getAvailableCars();
            if(!cars.isEmpty())
                return new ResponseEntity<>(cars, HttpStatus.OK);
            else
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") Long id) {
        Car car = carService.getCar(id);
        if(car != null)
            return new ResponseEntity<>(car,HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping("add")
    public ResponseEntity<String> addCar(@RequestBody() Car car) {
        try {
            Car newCar = carService.createCar(car);
            if(newCar != null)
                return new ResponseEntity<>("Success",HttpStatus.OK);
            return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        Car updatedCar = carService.updateCar(id,car);
        if(updatedCar==null)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updatedCar,HttpStatus.OK);
    }
}
