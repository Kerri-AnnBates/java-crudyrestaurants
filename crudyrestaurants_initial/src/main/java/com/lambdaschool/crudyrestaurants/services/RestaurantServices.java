package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.models.Restaurant;

public interface RestaurantServices {
    // Allow other classes to save a payment.
    Restaurant save(Restaurant restaurant);
}
