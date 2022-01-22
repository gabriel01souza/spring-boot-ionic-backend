package com.course.coursemc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemPedido implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    private Double desconto;
    private Integer quantity;
    private Double price;

   public ItemPedido(){
   }

    public ItemPedido(Pedido pedido, Product product, Double desconto, Integer quantity, Double price) {
        id.setPedido(pedido);
        id.setProduct(product);
        this.desconto = desconto;
        this.quantity = quantity;
        this.price = price;
    }

    public double getSubTotal(){
       return (price - desconto) * quantity;
    }

    @JsonIgnore
    public Pedido getPedido(){
       return id.getPedido();
    }

    public Product getProduct(){
       return id.getProduct();
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedido)) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
