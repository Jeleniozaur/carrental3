package com.jeleniozaur.carrental.car.controller;

import com.jeleniozaur.carrental.car.model.Car;
import com.jeleniozaur.carrental.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    CarRepository carRepository;

    //get all cars or by rented or by userId
    @GetMapping("cars")
    public ResponseEntity<List<Car>> getAllCars(@RequestParam(required = false) Long userId,
                                                @RequestParam(required = false) boolean rented) {
        try {
            List<Car> cars = new ArrayList<Car>();
            if (rented) {
                if (userId != null) {
                    //rented for user
                    carRepository.findByRentedToUserId(userId).forEach(cars::add);
                } else {
                    //return all rented cars
                    carRepository.findByRented(true).forEach(cars::add);
                }
            } else {
                if (userId == null) {
                    //return all not rented cars
                    carRepository.findByRented(false).forEach(cars::add);
                }
                //if both rented and carId are provided, the list will always be empty
                //this is due to a decision that while the car.rented is false the car.tented_to_user_id must be null
            }

            if (cars.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {

    //get car by id

    //get not rented cars

    //get cars rented by user

    /*update car
    allow:
        -model
        -brand
     */

    //update rented

    //delete car
}
