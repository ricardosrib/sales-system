package com.example.sales_system.domain.services;

import com.example.sales_system.domain.model.ProductModel;
import com.example.sales_system.domain.model.StockItemModel;
import com.example.sales_system.repository.repository_interface.IProductRepository;
import com.example.sales_system.repository.repository_interface.IStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    private IStockRepository stock;
    private IProductRepository products;

    public StockService(IProductRepository products, IStockRepository stock) {
        this.products = products;
        this.stock = stock;
    }

    public List<ProductModel> allProducts() {
        return stock.findAll();
    }

    public List<ProductModel> availableProducts() {
        return stock.findAllWithStock();
    }

    public ProductModel productById(long id) {
        return this.products.findById(id);
    }

    public int stockQuantity(long id) {
        return stock.getStockQuantity(id);
    }

    public StockItemModel addStock(long id, int quantity) {
        StockItemModel item = stock.findById(id);
        if (item == null) {
            return null;
        }
        if (item.getQuantity() + quantity > item.getMaxStock()) {
            item.setQuantity(item.getMaxStock());
            stock.save(item);
        } else {
            int newQuantity = item.getQuantity() + quantity;
            item.setQuantity(newQuantity);
            stock.save(item);
        }
        return item;
    }

    public void deductStock(long id, int quantity) {
        StockItemModel item = stock.findById(id);
        if (item == null) {
            throw new IllegalArgumentException("Product does not exist.");
        }
        if (item.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock quantity");
        }
        int newQuantity = item.getQuantity() - quantity;
        item.setQuantity(newQuantity);
        stock.save(item);
    }
}
