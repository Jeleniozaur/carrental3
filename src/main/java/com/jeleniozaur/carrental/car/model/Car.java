package com.jeleniozaur.carrental.car.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "rented")
    private boolean rented;

    @Column(name = "rented_to_user_id")
    private Long rentedToUserId;

    //constructor for car update
    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    //this constructor is used only for testing
    public Car(String brand, String model, boolean rented) {
        this.brand = brand;
        this.model = model;
        this.rented = rented;
    }
}
