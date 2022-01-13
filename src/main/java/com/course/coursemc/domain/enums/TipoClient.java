package com.course.coursemc.domain.enums;

import org.hibernate.boot.model.naming.IllegalIdentifierException;

public enum TipoClient {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int codigo;
    private String descrição;
    private TipoClient(int codigo, String descrição) {
        this.codigo = codigo;
        this.descrição = descrição;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescrição() {
        return descrição;
    }

    public static TipoClient toEnum(Integer codigo){
        if(codigo == null){
            return null;
        }

        for(TipoClient x : TipoClient.values()){
            if(codigo.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + codigo);
    }
}
