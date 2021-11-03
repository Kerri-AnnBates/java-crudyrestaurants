package com.lambdaschool.crudyrestaurants.repositories;

import com.lambdaschool.crudyrestaurants.models.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Restaurant findByName(String name);
    List<Restaurant> findByNameContainingIgnoreCase(String subname);
    List<Restaurant> findByMenus_dishContainingIgnoreCase(String subdish);
}
