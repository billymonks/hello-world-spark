package com.inventory.dao;

import com.inventory.car.Car;

import java.util.List;

/**
 * Created by wmonks on 3/23/2016.
 */
public interface CarDao {
    List<Car> getCars();

    //List<Car> getCarsByMake(String s);

    void insertCar(Car c);
}
