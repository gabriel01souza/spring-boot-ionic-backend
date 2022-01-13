package com.course.coursemc;

import com.course.coursemc.domain.Categoria;
import com.course.coursemc.domain.City;
import com.course.coursemc.domain.Product;
import com.course.coursemc.domain.State;
import com.course.coursemc.repositories.CategoriaRepository;
import com.course.coursemc.repositories.CityRepository;
import com.course.coursemc.repositories.ProductRepository;
import com.course.coursemc.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CoursemcApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;

    public static void main(String[] args) {
        SpringApplication.run(CoursemcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria categoria1 = new Categoria(null, "Informática");
        Categoria categoria2 = new Categoria(null, "Escritório");

        Product product1 = new Product(null, "Computer", 2000.00);
        Product product2 = new Product(null, "Impressora", 800.00);
        Product product3 = new Product(null, "Mouse", 80.00);

        categoria1.getProducts().addAll(Arrays.asList(product1, product2, product3));
        categoria2.getProducts().addAll(Arrays.asList(product2));

        product1.getCategorias().addAll(Arrays.asList(categoria1));
        product2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
        product3.getCategorias().addAll(Arrays.asList(categoria1));

        State state1 = new State(null, "Minas Gerais");
        State state2 = new State(null, "São Paulo");

        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
        productRepository.saveAll(Arrays.asList(product1, product2, product3));

        City city1 = new City(null, "Uberlândia", state1);
        City city2 = new City(null, "São Paulo", state2);
        City city3 = new City(null, "Campinas", state2);

        state1.getCities().addAll(Arrays.asList(city1));
        state2.getCities().addAll(Arrays.asList(city2, city3));

        stateRepository.saveAll(Arrays.asList(state1, state2));
        cityRepository.saveAll(Arrays.asList(city1, city2,city3));

    }
}
