package org.example.fooddelivery.service;

import org.example.fooddelivery.entity.Menu;
import org.example.fooddelivery.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu getMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
    }

    public List<Menu> getMenusByRestaurant(Long restaurantId) {
        return menuRepository.findByRestaurantId(restaurantId);
    }

    public List<Menu> searchMenu(String name) {
        return menuRepository.findByNameContainingIgnoreCase(name);
    }

    public Menu updateMenu(Long id, Menu updatedMenu) {

        Menu menu = getMenuById(id);

        menu.setName(updatedMenu.getName());
        menu.setDescription(updatedMenu.getDescription());
        menu.setPrice(updatedMenu.getPrice());
        menu.setAvailable(updatedMenu.getAvailable());

        return menuRepository.save(menu);
    }

    public void deleteMenu(Long id) {
        Menu menu = getMenuById(id);
        menuRepository.delete(menu);
    }
}