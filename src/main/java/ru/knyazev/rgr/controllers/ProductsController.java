package ru.knyazev.rgr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.knyazev.rgr.models.Inventory;
import ru.knyazev.rgr.models.Product;
import ru.knyazev.rgr.models.Supplier;
import ru.knyazev.rgr.services.ProductsService;


@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", productsService.findAll());
        return "products/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("supplier") Supplier supplier, @ModelAttribute("inventory")Inventory inventory) {
        model.addAttribute("product", productsService.findOne(id));
        Supplier productOwner = productsService.getProductOwner(id);
        model.addAttribute("owner", productOwner);
        Inventory inventoryOwner = productsService.getProductInventory(id);
        return "products/show";
    }

    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product) {
        return "products/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") Product product,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "products/new";

        productsService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", productsService.findOne(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") Product product, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "products/edit";

        productsService.update(id, product);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productsService.delete(id);
        return "redirect:/products";
    }
}
