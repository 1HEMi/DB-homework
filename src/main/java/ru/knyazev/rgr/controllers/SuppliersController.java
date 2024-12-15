package ru.knyazev.rgr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.knyazev.rgr.models.Product;
import ru.knyazev.rgr.models.Supplier;
import ru.knyazev.rgr.services.SuppliersJdbcService;

import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SuppliersController {
    private final SuppliersJdbcService suppliersService;

    @Autowired
    public SuppliersController(SuppliersJdbcService suppliersService) {
        this.suppliersService = suppliersService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("suppliers", suppliersService.findAll());
        return "suppliers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Supplier supplier = suppliersService.findOne(id);
        List<Product> products = suppliersService.getProductsBySupplierId(id);
        model.addAttribute("supplier", supplier);
        model.addAttribute("products", products);
        return "suppliers/show";
    }

    @GetMapping("/new")
    public String newSupplier(@ModelAttribute("supplier") Supplier supplier) {
        return "suppliers/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("supplier") Supplier supplier,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "suppliers/new";

        suppliersService.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Supplier supplier = suppliersService.findOne(id);
        model.addAttribute("supplier", supplier);
        return "suppliers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("supplier") Supplier supplier, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "suppliers/edit";

        suppliersService.update(id, supplier);
        return "redirect:/suppliers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        suppliersService.delete(id);
        return "redirect:/suppliers";
    }
}
