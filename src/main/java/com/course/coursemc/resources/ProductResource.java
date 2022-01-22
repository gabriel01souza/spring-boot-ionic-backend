package com.course.coursemc.resources;

import com.course.coursemc.domain.Product;
import com.course.coursemc.dto.ProductDTO;
import com.course.coursemc.resources.utils.URL;
import com.course.coursemc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable Integer id) {
        Product obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value="name", defaultValue="") String name,
            @RequestParam(value="categorias", defaultValue="") String categorias,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="name") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        String nameDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Product> list = service.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
