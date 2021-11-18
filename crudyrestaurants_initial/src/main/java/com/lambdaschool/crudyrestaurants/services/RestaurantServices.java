package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.models.Restaurant;
import com.lambdaschool.crudyrestaurants.views.MenuCounts;

import java.util.List;

public interface RestaurantServices {
    // Allow other classes to save a payment.
    Restaurant save(Restaurant restaurant);
    List<Restaurant> findAllRestaurants();
    Restaurant findRestaurantById(long id);
    Restaurant findRestaurantByName(String name);
    List<Restaurant> findByNameLike(String subname);
    List<Restaurant> findByDishLike(String subdish);
    List<MenuCounts> getMenuCounts();
    void deleteById(long id);
}
