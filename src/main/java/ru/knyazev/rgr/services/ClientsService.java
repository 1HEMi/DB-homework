package ru.knyazev.rgr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knyazev.rgr.models.Client;
import ru.knyazev.rgr.models.Order;
import ru.knyazev.rgr.repositories.ClientsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientsService {
    private final ClientsRepository clientsRepository;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public List<Client> findAll(){
        return clientsRepository.findAll();
    }

    public Client findOne(int id){
        Optional<Client> foundClient = clientsRepository.findById(id);

        return foundClient.orElse(null);
    }

    public List<Order> getOrdersByClientId(int id){
        Optional<Client> client = clientsRepository.findById(id);

        return client.get().getOrders();
    }

    @Transactional
    public void save(Client client) {
        clientsRepository.save(client);
    }

    @Transactional
    public void update(int id, Client updatedClient) {
        updatedClient.setId(id);
        clientsRepository.save(updatedClient);
    }

    @Transactional
    public void delete(int id) {
        clientsRepository.deleteById(id);

    }
}
