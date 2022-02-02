package com.course.coursemc.domain.enums;

public enum Perfil {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT");


    private int codigo;
    private String descrição;


    Perfil(int codigo, String descrição) {
        this.codigo = codigo;
        this.descrição = descrição;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescrição() {
        return descrição;
    }

    public static Perfil toEnum(Integer codigo){
        if(codigo == null){
            return null;
        }

        for(Perfil x : Perfil.values()){
            if(codigo.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + codigo);
    }

}
