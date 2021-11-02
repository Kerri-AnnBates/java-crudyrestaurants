package com.lambdaschool.crudyrestaurants.controllers;

import com.lambdaschool.crudyrestaurants.models.Restaurant;
import com.lambdaschool.crudyrestaurants.services.RestaurantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {
    @Autowired
    RestaurantServices restaurantServices;

    // http://localhost:2019/restaurants/restaurants
    @GetMapping(value = "/restaurants", produces = "application/json")
    public ResponseEntity<?> listAllRestaraunts() {
        List<Restaurant> mylist = restaurantServices.findAllRestaurants();

        return new ResponseEntity<>(mylist, HttpStatus.OK);
    }

    // http://localhost:2019/restaurants/3
    // http://localhost:2019/restaurants/restaurant/name/Good Eats
    // http://localhost:2019/restaurants/restaurant/nameLike/good
    // http://localhost:2019/restaurants/menucount

    // http://localhost:2019/restaurants/restaurant/likedish/cake
}
