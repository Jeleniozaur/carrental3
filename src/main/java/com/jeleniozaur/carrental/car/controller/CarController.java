package com.jeleniozaur.carrental.car.controller;

import com.jeleniozaur.carrental.car.model.Car;
import com.jeleniozaur.carrental.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car/")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("all")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        if(!cars.isEmpty())
            return new ResponseEntity<>(cars, HttpStatus.OK);
        else
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    @GetMapping("available")
    public ResponseEntity<List<Car>> getAvailableCars() {
        List<Car> cars = carService.getAvailableCars();
        if(!cars.isEmpty())
            return new ResponseEntity<>(cars, HttpStatus.OK);
        else
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") Long id) {
        Car car = carService.getCar(id);
        if(car != null)
            return new ResponseEntity<>(car,HttpStatus.OK);
        return new ResponseEntity("Car not found",null,HttpStatus.NOT_FOUND);
    }

    @PostMapping("add")
    public ResponseEntity<String> addCar(@RequestBody() Car car) {
        try {
            carService.createCar(car);
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        Car updatedCar = carService.updateCar(id,car);
        if(updatedCar!=null)
            return new ResponseEntity<>(updatedCar,HttpStatus.OK);
        return new ResponseEntity("Invalid car id",null,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteCar(@PathVariable("id") Long id) {
        try {
            carService.deleteCar(id);
            return new ResponseEntity("Success",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("rent/{carId}")
    public ResponseEntity rentCar(@PathVariable("carId") Long carId, @RequestHeader("userId") Long userId) {
        try {
            carService.rentCar(carId,userId);
            return new ResponseEntity("Success",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("return/{id}")
    public ResponseEntity returnCar(@PathVariable("id") Long id) {
        try {
            carService.returnCar(id);
            return new ResponseEntity("Success",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
