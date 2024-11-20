package ru.knyazev.rgr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.knyazev.rgr.models.Client;
import ru.knyazev.rgr.models.Supplier;
import ru.knyazev.rgr.services.ClientsService;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    private final ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("clients", clientsService.findAll());
        return "clients/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientsService.findOne(id));
        model.addAttribute("orders", clientsService.getOrdersByClientId(id));
        return "clients/show";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute("client") Client client) {
        return "clients/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("client") Client client,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "clients/new";

        clientsService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("client", clientsService.findOne(id));
        return "clients/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("client") Client client, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "clients/edit";

        clientsService.update(id, client);
        return "redirect:/clients";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        clientsService.delete(id);
        return "redirect:/clients";
    }
}
