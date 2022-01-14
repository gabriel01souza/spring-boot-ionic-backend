package com.course.coursemc.services;

import com.course.coursemc.domain.Categoria;
import com.course.coursemc.domain.Pedido;
import com.course.coursemc.repositories.CategoriaRepository;
import com.course.coursemc.repositories.PedidoRepository;
import com.course.coursemc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido buscar(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "
                + id + ", tipo: " + Pedido.class.getName()));
    }

}
