package com.example.sales_system.repository.jpa_interface;

import org.springframework.data.repository.ListCrudRepository;

import com.example.sales_system.repository.jpa_entities.ProductEntity;

public interface ProductJPA extends ListCrudRepository<ProductEntity, Long> {

}