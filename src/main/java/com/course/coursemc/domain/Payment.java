package com.course.coursemc.domain;

import com.course.coursemc.domain.enums.EstadoPayment;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

    @Id
    private Integer id;
    private Integer estado;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    private Pedido pedido;

    public Payment(){
    }

    public Payment(Integer id, EstadoPayment estado, Pedido pedido) {
        this.id = id;
        this.estado = estado.getCodigo();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPayment getEstado() {
        return EstadoPayment.toEnum(estado);
    }

    public void setEstado(EstadoPayment estado) {
        this.estado = estado.getCodigo();
    }

    public Pedido getOrder() {
        return pedido;
    }

    public void setOrder(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
