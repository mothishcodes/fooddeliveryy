package org.example.fooddelivery.controller;

import org.example.fooddelivery.entity.Menu;
import org.example.fooddelivery.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.createMenu(menu);
    }

    @GetMapping
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/{id}")
    public Menu getMenuById(@PathVariable Long id) {
        return menuService.getMenuById(id);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Menu> getMenusByRestaurant(
            @PathVariable Long restaurantId) {

        return menuService.getMenusByRestaurant(restaurantId);
    }

    @GetMapping("/search")
    public List<Menu> searchMenu(@RequestParam String name) {
        return menuService.searchMenu(name);
    }

    @PutMapping("/{id}")
    public Menu updateMenu(
            @PathVariable Long id,
            @RequestBody Menu menu) {

        return menuService.updateMenu(id, menu);
    }

    @DeleteMapping("/{id}")
    public String deleteMenu(@PathVariable Long id) {

        menuService.deleteMenu(id);

        return "Menu item deleted successfully";
    }
}