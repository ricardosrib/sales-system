package com.example.sales_system.repository.interfaceJPA;

import com.example.sales_system.repository.entitiesJPA.StockItemEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface StockJPA extends ListCrudRepository<StockItemEntity,Long> {
    Optional<StockItemEntity> findByProductId(long id);
}