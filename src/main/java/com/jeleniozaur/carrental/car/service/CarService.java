package com.jeleniozaur.carrental.car.service;

import com.jeleniozaur.carrental.car.model.Car;

import java.util.List;

public interface CarService {
    public List<Car> getAllCars();
    public List<Car> getAvailableCars();
    public Car getCar(Long id);
    public void createCar(Car car) throws Exception;
    public void deleteCar(Long id) throws Exception;
    public Car updateCar(Long id, Car car);
    public void rentCar(Long carId, Long userId) throws Exception;
    public void returnCar(Long carId) throws Exception;
}
