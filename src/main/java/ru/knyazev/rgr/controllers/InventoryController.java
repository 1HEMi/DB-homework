package ru.knyazev.rgr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.knyazev.rgr.models.Inventory;
import ru.knyazev.rgr.models.Supplier;
import ru.knyazev.rgr.services.InventoryService;

@Controller
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("inventories", inventoryService.findAll());
        return "inventories/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("inventory", inventoryService.findOne(id));
        model.addAttribute("products", inventoryService.getProductsByInventoryId(id));
        return "inventories/show";
    }

    @GetMapping("/new")
    public String newInventory(@ModelAttribute("inventory") Inventory inventory) {
        return "inventories/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("inventory") Inventory inventory,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "inventories/new";

        inventoryService.save(inventory);
        return "redirect:/inventories";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("inventory", inventoryService.findOne(id));
        return "inventories/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("inventory") Inventory inventory, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "inventories/edit";

        inventoryService.update(id, inventory);
        return "redirect:/inventories";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        inventoryService.delete(id);
        return "redirect:/inventories";
    }
}
