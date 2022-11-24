package com.jeleniozaur.carrental.car.service;

import com.jeleniozaur.carrental.car.model.Car;
import com.jeleniozaur.carrental.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getAvailableCars() {
        return carRepository.findByRented(false);
    }
    @Override
    public Car getCar(Long id) {
        if(carExists(id))
            return carRepository.findById(id).get();
        return null;
    }

    @Override
    public Car createCar(Car car) {
        if(car.getModel() != null && car.getBrand() != null) {
            Car newCar = new Car();
            newCar.setModel(car.getModel());
            newCar.setBrand(car.getBrand());
            return carRepository.save(newCar);
        }
        return null;
    }

    @Override
    public Car deleteCar(Long id) {
        if(carExists(id)) {
            carRepository.deleteById(id);
            return getCar(id);
        }
        return null;
    }

    @Override
    public Car updateCar(Long id, Car car) {
        if(carExists(id)) {
            Car updatedCar = getCar(id);
            if(car.getModel()!=null) { updatedCar.setModel(car.getModel()); }
            if(car.getBrand()!=null) { updatedCar.setBrand(car.getBrand()); }
            carRepository.save(updatedCar);
            return updatedCar;
        }
        return null;
    }

    @Override
    public void rentCar(Long carId, Long userId) {
        System.out.println("rented a car");
    }

    private boolean carExists(Long id) {
        return carRepository.findById(id).isPresent();
    }
}
