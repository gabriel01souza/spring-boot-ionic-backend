package com.course.coursemc.services;

import com.course.coursemc.domain.Categoria;
import com.course.coursemc.domain.Product;
import com.course.coursemc.repositories.CategoriaRepository;
import com.course.coursemc.repositories.ProductRepository;
import com.course.coursemc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Product find(Integer id) {
        Optional<Product> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "
                + id + ", tipo: " + Product.class.getName()));
    }

    public Page<Product> search(String name, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repo.findDistincByNameContainingAndCategoriasIn(name, categorias , pageRequest);
    }
}
