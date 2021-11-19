package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.models.Menu;
import com.lambdaschool.crudyrestaurants.models.Payment;
import com.lambdaschool.crudyrestaurants.models.Restaurant;
import com.lambdaschool.crudyrestaurants.repositories.PaymentRepository;
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
public class RestaurantServicesImpl implements RestaurantServices {

    // Give access to Restaurant Repository
    @Autowired
    RestaurantRepository restaurantRepos;

    @Autowired
    PaymentRepository paymentRepos;

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        Restaurant newRestaurant = new Restaurant();

        if (restaurant.getRestaurantid() != 0) {
            restaurantRepos.findById(restaurant.getRestaurantid())
                    .orElseThrow(() -> new EntityNotFoundException("Restaurant " + restaurant.getRestaurantid() + " not found!"));

            newRestaurant.setRestaurantid(restaurant.getRestaurantid());
        }

        // handle primitive data types
        newRestaurant.setName(restaurant.getName());
        newRestaurant.setAddress(restaurant.getAddress());
        newRestaurant.setCity(restaurant.getCity());
        newRestaurant.setState(restaurant.getState());
        newRestaurant.setTelephone(restaurant.getTelephone());
        newRestaurant.setSeatcapacity(restaurant.getSeatcapacity());

        // handle collections - many to many
        newRestaurant.getPayments().clear();

        for (Payment p : restaurant.getPayments()) {
            Payment newPay = paymentRepos.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " not found!"));

            newRestaurant.getPayments().add(newPay);
        }

        // handle collections - one to many
        newRestaurant.getMenus().clear();

        for (Menu m : restaurant.getMenus()) {
            Menu newMenu = new Menu();

            newMenu.setDish(m.getDish());
            newMenu.setPrice(m.getPrice());
            newMenu.setRestaurant(newRestaurant); // link menu item to restaurant

            newRestaurant.getMenus().add(newMenu);
        }

        return restaurantRepos.save(newRestaurant);
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
        if (restaurant == null) {
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

    @Transactional
    @Override
    public Restaurant update(Restaurant restaurant, long id) {
        Restaurant currRestaurant = restaurantRepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant " + id + " not found!"));

        // handle primitive data types
        if (restaurant.getName() != null) {
            currRestaurant.setName(restaurant.getName());
        }

        if (restaurant.getAddress() != null) {
            currRestaurant.setAddress(restaurant.getAddress());
        }

        if (restaurant.getCity() != null) {
            currRestaurant.setCity(restaurant.getCity());
        }

        if (restaurant.getState() != null) {
            currRestaurant.setState(restaurant.getState());
        }

        if (restaurant.getTelephone() != null) {
            currRestaurant.setTelephone(restaurant.getTelephone());
        }

        if(restaurant.hasvalueforseatcapacity) {
            currRestaurant.setSeatcapacity(restaurant.getSeatcapacity());
        }

        // handle collections - many to many
        if (restaurant.getPayments().size() > 0) {
            currRestaurant.getPayments().clear();

            for (Payment p : restaurant.getPayments()) {
                Payment newPay = paymentRepos.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " not found!"));

                currRestaurant.getPayments().add(newPay);
            }
        }

        // handle collections - one to many
        if (restaurant.getMenus().size() > 0) {
            currRestaurant.getMenus().clear();

            for (Menu m : restaurant.getMenus()) {
                Menu newMenu = new Menu();

                newMenu.setDish(m.getDish());
                newMenu.setPrice(m.getPrice());
                newMenu.setRestaurant(currRestaurant); // link menu item to restaurant

                currRestaurant.getMenus().add(newMenu);
            }
        }

        return restaurantRepos.save(currRestaurant);
    }


}
