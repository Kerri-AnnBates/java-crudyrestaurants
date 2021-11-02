package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.models.Restaurant;
import com.lambdaschool.crudyrestaurants.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "restaurantService")
public class RestaurantServicesImpl  implements RestaurantServices {

    // Give access to Restaurant Repository
    @Autowired
    RestaurantRepository restaurantRepos;

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepos.save(restaurant);
    }

    @Override
    public List<Restaurant> findAllRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        restaurantRepos.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public Restaurant findRestaurantById(long id) {
        return null;
    }

    @Override
    public Restaurant findRestaurantByName(String name) {
        return null;
    }

    @Override
    public List<Restaurant> findByNameLike(String subname) {
        return null;
    }

    @Override
    public List<Restaurant> findByDishLike(String subdish) {
        return null;
    }
}
