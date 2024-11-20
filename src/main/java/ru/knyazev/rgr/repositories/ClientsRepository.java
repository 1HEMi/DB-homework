package ru.knyazev.rgr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knyazev.rgr.models.Client;

public interface ClientsRepository extends JpaRepository<Client, Integer> {
}
