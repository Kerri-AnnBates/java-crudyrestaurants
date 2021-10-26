package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.models.Menu;

public interface MenuServices {
    // Allow other classes to save a payment.
    Menu save(Menu menu);
}
