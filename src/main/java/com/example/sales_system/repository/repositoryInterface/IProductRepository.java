package com.example.sales_system.repository.repositoryInterface;

import com.example.sales_system.domain.model.ProductModel;

import java.util.List;

public interface IProductRepository {
    List<ProductModel> findAll();
    ProductModel findById(long id);
}
