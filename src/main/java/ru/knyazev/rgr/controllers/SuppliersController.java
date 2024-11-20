package ru.knyazev.rgr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.knyazev.rgr.models.Supplier;
import ru.knyazev.rgr.services.SuppliersService;

@Controller
@RequestMapping("/suppliers")
public class SuppliersController {
    private final SuppliersService suppliersService;

    @Autowired
    public SuppliersController(SuppliersService suppliersService) {
        this.suppliersService = suppliersService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("suppliers", suppliersService.findAll());
        return "suppliers/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("supplier", suppliersService.findOne(id));
        model.addAttribute("products", suppliersService.getProductsBySupplierId(id));
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
        model.addAttribute("supplier", suppliersService.findOne(id));
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
