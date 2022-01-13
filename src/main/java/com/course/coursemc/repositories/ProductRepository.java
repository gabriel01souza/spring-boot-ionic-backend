package com.course.coursemc.repositories;

import com.course.coursemc.domain.Categoria;
import com.course.coursemc.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
