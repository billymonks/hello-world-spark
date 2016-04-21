package com.inventory.dao.impl;

import com.inventory.car.Car;
import com.inventory.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wmonks on 3/23/2016.
 */
@Repository
public class CarDaoImpl implements CarDao {

    private NamedParameterJdbcTemplate template;

    @Autowired
    public CarDaoImpl(DataSource ds) {
        template = new NamedParameterJdbcTemplate(ds);
    }

    @Override
    public List<Car> getCars() {
        Map<String, Object> params = new HashMap<String, Object>();

        String sql = "select make, model, price from car";

        List<Car> result = template.query(sql, params, carMapper);

        return result;
    }

    @Override
    public void insertCar(Car c) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("make", c.getMake());
        params.put("model", c.getModel());
        params.put("price", c.getPrice());

        String sql = "insert into car (make, model, price) values (:make, :model, :price)";
        template.update(sql, params);
    }

    //@Override
    //public List<Car> getCarsByMake(String make) {
        //Map<String, Object> params = new HashMap<~>();
        //params.put("make", make);

        //String sql = "select make, model, price from car where make = (:make)";
        //template.update(sql, params);
    //}



    private RowMapper<Car> carMapper = (rs, rowNum) -> {
        Car c = new Car();

        c.setMake(rs.getString("make"));
        c.setModel(rs.getString("model"));
        c.setPrice(rs.getInt("price"));

        return c;
    };
}
