package ru.knyazev.rgr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.knyazev.rgr.models.Client;
import ru.knyazev.rgr.models.Order;
import ru.knyazev.rgr.models.Product;
import ru.knyazev.rgr.models.Supplier;
import ru.knyazev.rgr.services.OrdersService;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("orders", ordersService.findAll());
        return "orders/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("client") Client client) {
        model.addAttribute("order", ordersService.findOne(id));
        Client orderOwner = ordersService.getOrderOwner(id);
        model.addAttribute("ownerClient", orderOwner);
        model.addAttribute("products", ordersService.getProductsByOrderId(id));
        return "orders/show";
    }

    @GetMapping("/new")
    public String newOrder(@ModelAttribute("order") Order order) {
        return "orders/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("order") Order order,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "orders/new";

        ordersService.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("order", ordersService.findOne(id));
        return "orders/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("order") Order order, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "orders/edit";

        ordersService.update(id, order);
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ordersService.delete(id);
        return "redirect:/orders";
    }


}
