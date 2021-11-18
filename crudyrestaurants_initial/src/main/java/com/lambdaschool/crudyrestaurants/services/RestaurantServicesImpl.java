package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.models.Restaurant;
import com.lambdaschool.crudyrestaurants.repositories.RestaurantRepository;
import com.lambdaschool.crudyrestaurants.views.MenuCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
        return restaurantRepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant id: " + id + " not found!"));
    }

    @Override
    public Restaurant findRestaurantByName(String name) {
        Restaurant restaurant = restaurantRepos.findByName(name);
        if(restaurant == null) {
            throw new EntityNotFoundException("Restaurant " + name + " not found!");
        }

        return restaurant;
    }

    @Override
    public List<Restaurant> findByNameLike(String subname) {
        return restaurantRepos.findByNameContainingIgnoreCase(subname);
    }

    @Override
    public List<Restaurant> findByDishLike(String subdish) {
        return restaurantRepos.findByMenus_dishContainingIgnoreCase(subdish);
    }

    @Override
    public List<MenuCounts> getMenuCounts() {
        return restaurantRepos.findMenuCounts();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        if (restaurantRepos.findById(id).isPresent()) {
            restaurantRepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Restaurant " + id + " not found!");
        }
    }


}
