package com.course.coursemc.dto;

import com.course.coursemc.domain.Categoria;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private Integer id;

   // @NotEmpty(message="Preenchimento obrigatório")
    //@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
   // @Max(value = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
   // @Min(5)
    private String name;

    public CategoriaDTO(){
    }

    public CategoriaDTO(Categoria obj){
        id = obj.getId();
        name = obj.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
