package com.course.coursemc.domain;

import com.course.coursemc.domain.enums.EstadoPayment;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;


@Entity
@JsonTypeName("paymentComCard")
public class PaymentComCard extends Payment{

    private Integer numeroDeParcelas;

    public PaymentComCard() {

    }

    public PaymentComCard(Integer id, EstadoPayment estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
