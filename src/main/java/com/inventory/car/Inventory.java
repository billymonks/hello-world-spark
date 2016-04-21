package com.inventory.car;

import java.io.*;
import java.util.*;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by wmonks on 3/23/2016.
 */
public class Inventory {
    List<Map<String, Object>> cars;


    public Inventory() {
        //cars = getCars("");
        cars = new ArrayList<Map<String, Object>>();
    }

    static List<Map<String, Object>> getCars(String connectionUrl) {
        try {
            URLConnection request = new URL(connectionUrl).openConnection();
            request.connect();
            return (List) new JSONParser().parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new ArrayList<Map<String, Object>>();
    }

    public void Add() {
        Map<String, Object> car = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> cars = (List) new JSONParser().parse("{\"carid\":\"testtesttest\",\"year\":\"2012\",\"make\":\"Ford\",\"model\":\"Focus\",\"trim\":\"SE\",\"price\":\"$99,300\",\"mileage\":27133,\"body_style\":\"Hatchback\",\"transmission\":\"Automatic\"");
            car = cars.get(0);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cars.add(car);

    }
}
