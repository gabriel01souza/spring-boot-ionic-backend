package com.course.coursemc.services;

import com.course.coursemc.domain.Client;
import com.course.coursemc.repositories.ClientRepository;
import com.course.coursemc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    public Client find(Integer id) {
        Optional<Client> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "
                + id + ", tipo: " + Client.class.getName()));
    }

}
