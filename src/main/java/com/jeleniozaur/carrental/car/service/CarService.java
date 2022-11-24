package com.jeleniozaur.carrental.car.service;

import com.jeleniozaur.carrental.car.model.Car;

import java.util.List;

public interface CarService {
    public List<Car> getAllCars();
    public List<Car> getAvailableCars();
    public Car getCar(Long id);
    public Car createCar(Car car);
    public Car deleteCar(Long id);
    public Car updateCar(Long id, Car car);
    public void rentCar(Long carId, Long userId);
}
