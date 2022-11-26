package com.jeleniozaur.carrental.car.service;

import com.jeleniozaur.carrental.car.model.Car;
import com.jeleniozaur.carrental.car.repository.CarRepository;
import com.jeleniozaur.carrental.user.model.User;
import com.jeleniozaur.carrental.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;
    @Autowired
    UserService userService;

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
    public void createCar(Car car) throws Exception {
        if(car.getModel() != null && car.getBrand() != null) {
            Car newCar = new Car();
            newCar.setModel(car.getModel());
            newCar.setBrand(car.getBrand());
            carRepository.save(newCar);
        }
        else {
            throw new Exception("Car model and brand can not be null");
        }

    }

    @Override
    public void deleteCar(Long id) throws Exception {
        if(carExists(id))
            carRepository.deleteById(id);
        else
            throw new Exception("Car not found");
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
    public void rentCar(Long carId, Long userId) throws Exception {
        boolean carExists = carExists(carId);
        User user = userService.getUser(userId);
        boolean userExists = user != null;
        if(carExists && userExists) {
            if(!getCar(carId).isRented()) {
                updateRentedAndUserToRented(carId,userId,true);
            }
            else
                throw new Exception("Car already rented.");
        }
        else if(!carExists && userExists) {
            throw new Exception("Invalid car id.");
        }
        else if(carExists && !userExists) {
            throw new Exception("Invalid user id.");
        }
        else if(!carExists && !userExists){
            throw new Exception("Invalid user and car ids.");
        }
    }

    @Override
    public void returnCar(Long carId) throws Exception {
        if(carExists(carId)) {
            if(getCar(carId).isRented()) {
                updateRentedAndUserToRented(carId,null,false);
            }
            else
                throw new Exception("Car is not rented.");
        }
        else
            throw new Exception("Invalid car id.");
    }

    private void updateRentedAndUserToRented(Long carId, Long userId,boolean rented) {
        Car updatedCar = getCar(carId);
        updatedCar.setRented(rented);
        updatedCar.setRentedToUserId(null);
        carRepository.save(updatedCar);
    }

    private boolean carExists(Long id) {
        return carRepository.findById(id).isPresent();
    }
}
