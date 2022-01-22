package com.course.coursemc.domain;

import com.course.coursemc.domain.enums.EstadoPayment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeName("paymentComBoleto")
public class PaymentComBoleto  extends Payment {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
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
