package com.course.coursemc.domain.enums;

public enum EstadoPayment {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int codigo;
    private String descrição;


    EstadoPayment(int codigo, String descrição) {
        this.codigo = codigo;
        this.descrição = descrição;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescrição() {
        return descrição;
    }

    public static EstadoPayment toEnum(Integer codigo){
        if(codigo == null){
            return null;
        }

        for(EstadoPayment x : EstadoPayment.values()){
            if(codigo.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + codigo);
    }

}
