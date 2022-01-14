package com.course.coursemc.domain;

import com.course.coursemc.domain.enums.EstadoPayment;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentComBoleto  extends Payment{

    private Date dataVencimento;
    private Date dataPayment;

    public PaymentComBoleto(){
    }

    public PaymentComBoleto(Integer id, EstadoPayment estado, Pedido pedido, Date dataVencimento, Date dataPayment) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPayment = dataPayment;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPayment() {
        return dataPayment;
    }

    public void setDataPayment(Date dataPayment) {
        this.dataPayment = dataPayment;
    }
}
