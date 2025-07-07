package com.example.sales_system.repository.interfaceJPA;

import com.example.sales_system.repository.entitiesJPA.ProductEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductJPA extends ListCrudRepository<ProductEntity, Long> {

}