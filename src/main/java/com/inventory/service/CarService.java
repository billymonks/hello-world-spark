package com.inventory.service;

import com.inventory.car.Car;
import com.inventory.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wmonks on 3/23/2016.
 */
@Service
public class CarService {

    @Autowired
    private CarDao carDao;

    public List<Car> getCars() {
        return carDao.getCars();
    }

    /*public List<Car> getMatchingCars(Car c) {
        if(c.getMake()!=null)
        {
            return carDao.getCarsByMake(c.getMake());
        }
    }*/

    public void submitCar(Car c) {
        carDao.insertCar(c);
    }

    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }


}
