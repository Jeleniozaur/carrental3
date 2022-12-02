package com.jeleniozaur.carrental.car.repository;

import com.jeleniozaur.carrental.car.model.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchReflectiveOperationException;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    CarRepository carRepository;

    @Test
    public void should_find_no_cars_if_none_exists() {
        List<Car> cars = carRepository.findAll();
        Car car = new Car("brand","model",false);
        carRepository.save(car);
        assertTrue(cars.isEmpty());
    }

    @Test
    public void should_store_a_car() {
        Car car = new Car("brand","model",false);
        carRepository.save(car);
        List<Car> cars = carRepository.findAll();
        assertTrue(cars.contains(car));
    }

    @Test
    public void should_find_all_cars() {
        List<Car> addedCars = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            Car c = new Car("brand"+i, "model"+i, false);
            carRepository.save(c);
            addedCars.add(c);
        }

        List<Car> cars = carRepository.findAll();
        assertTrue(cars.containsAll(addedCars));
    }

    @Test
    public void should_find_car_by_id() {
        Car car = new Car("brand","model",false);
        carRepository.save(car);
        Car foundCar = carRepository.findById(car.getId()).get();
        assertTrue(foundCar != null);
    }

    @Test
    public void should_find_cars_by_rented() {
        List<Car> notRentedCars = new ArrayList<>();
        List<Car> rentedCars = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            Car nrc = new Car("brand"+i, "model"+i, false);
            Car rc = new Car("brand"+i, "model"+i, true);
            carRepository.save(nrc);
            carRepository.save(rc);
            notRentedCars.add(nrc);
            rentedCars.add(rc);
        }

        List<Car> rentedCarsResult = carRepository.findByRented(true);
        List<Car> notRentedCarsResult = carRepository.findByRented(false);
        assertTrue(rentedCarsResult.containsAll(rentedCars) && notRentedCarsResult.containsAll(notRentedCars));
    }

    @Test
    public void should_delete_a_car_by_id() {
        Car car = new Car("brand","don't delete",false);
        Car carToDelete = new Car("delete","me",false);

        carRepository.save(car);
        carRepository.save(carToDelete);

        carRepository.deleteById(carToDelete.getId());
        assertTrue(carRepository.findAll().contains(car) && !carRepository.findAll().contains(carToDelete));
    }
}