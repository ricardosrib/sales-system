package com.example.sales_system.usecase.dto;

import com.example.sales_system.domain.model.OrderItemModel;

public class OrderDTO {
    
    private long productId;
    private int quantity;

    public OrderDTO(long productId, int quantity) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be positive");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "RequestedItemDTO [productId=" + productId + ", quantity=" + quantity + "]";
    }    

    public static OrderDTO fromModel(OrderItemModel item) {
        if (item == null) {
            throw new IllegalArgumentException("Order item model cannot be null");
        }
        if (item.getProduct() == null) {
            throw new IllegalArgumentException("Product in order item cannot be null");
        }
        return new OrderDTO(item.getProduct().getId(), item.getQuantity());
    }
}