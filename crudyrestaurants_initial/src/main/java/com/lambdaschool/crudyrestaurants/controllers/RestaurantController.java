package com.lambdaschool.crudyrestaurants.controllers;

import com.lambdaschool.crudyrestaurants.models.Restaurant;
import com.lambdaschool.crudyrestaurants.services.RestaurantServices;
import com.lambdaschool.crudyrestaurants.views.MenuCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // http://localhost:2019/restaurants/restaurant/3
    @GetMapping(value = "/restaurant/{id}", produces = "application/json")
    public ResponseEntity<?> findRestaurantById(@PathVariable long id) {
        Restaurant restaurant = restaurantServices.findRestaurantById(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    // http://localhost:2019/restaurants/restaurant/name/Good Eats
    @GetMapping(value = "/restaurant/name/{name}", produces = "application/json")
    public ResponseEntity<?> findRestaurantByName(@PathVariable String name) {
        Restaurant restaurant = restaurantServices.findRestaurantByName(name);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    // http://localhost:2019/restaurants/restaurant/nameLike/good
    @GetMapping(value = "/restaurant/nameLike/{subname}", produces = "application/json")
    public ResponseEntity<?> findRestaurantByLikeName(@PathVariable String subname) {
        List<Restaurant> list = restaurantServices.findByNameLike(subname);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // http://localhost:2019/restaurants/menucount
    @GetMapping(value = "/menucount", produces = "application/json")
    public ResponseEntity<?> getMenuCounts() {
        List<MenuCounts> menuCounts = restaurantServices.getMenuCounts();

        return new ResponseEntity<>(menuCounts, HttpStatus.OK);
    }

    // http://localhost:2019/restaurants/restaurant/likedish/cake
    @GetMapping(value = "/restaurant/restaurant/{subdish}", produces = "application/json")
    public ResponseEntity<?> findByLikeDish(@PathVariable String subdish) {
        List<Restaurant> list = restaurantServices.findByDishLike(subdish);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
