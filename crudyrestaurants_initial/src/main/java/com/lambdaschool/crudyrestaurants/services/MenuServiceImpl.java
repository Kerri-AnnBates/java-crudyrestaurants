package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.models.Menu;
import com.lambdaschool.crudyrestaurants.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MenuServiceImpl implements MenuServices{

    @Autowired
    MenuRepository menuRepos;

    @Transactional
    @Override
    public Menu save(Menu menu) {
        return menuRepos.save(menu);
    }
}
