package com.example.sales_system.repository.repositoryImpl;

import com.example.sales_system.domain.model.ProductModel;
import com.example.sales_system.repository.entitiesJPA.ProductEntity;
import com.example.sales_system.repository.interfaceJPA.ProductJPA;
import com.example.sales_system.repository.repositoryInterface.IProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
@Primary
public class ProductJPARepository implements IProductRepository {
    private ProductJPA productRepository;

    public ProductJPARepository(ProductJPA productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductModel> findAll() {
        List<ProductEntity> products = productRepository.findAll();
        if (products.isEmpty()) {
            return new LinkedList<>();
        } else {
            return products.stream()
                    .map(ProductEntity::toProductModel)
                    .toList();
        }
    }

    @Override
    public ProductModel findById(long id) {
        ProductEntity product = productRepository.findById(id).orElse(null);
        return (product == null) ? null : ProductEntity.toProductModel(product);
    }
}
