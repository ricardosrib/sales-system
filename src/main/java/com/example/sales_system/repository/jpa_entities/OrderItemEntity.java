package com.example.sales_system.repository.jpa_entities;

import com.example.sales_system.domain.model.OrderItemModel;
import com.example.sales_system.domain.model.ProductModel;
import jakarta.persistence.*;

@Entity
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private ProductEntity product;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private BudgetEntity budget;

    private int quantity;

    protected OrderItemEntity() {
    }

    public OrderItemEntity(ProductEntity product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderItem [product=" + product + ", quantity=" + quantity + "]";
    }

    public static OrderItemEntity fromOrderItemModel(OrderItemModel model) {
        ProductEntity product = ProductEntity.fromProductModel(model.getProduct());
        return new OrderItemEntity(product, model.getQuantity());
    }

    public static OrderItemModel toOrderItemModel(OrderItemEntity item) {
        ProductModel productModel = ProductEntity.toProductModel(item.getProduct());
        return new OrderItemModel(productModel, item.getQuantity());
    }
}
