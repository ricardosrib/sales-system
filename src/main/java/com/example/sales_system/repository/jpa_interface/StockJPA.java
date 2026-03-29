package com.example.sales_system.repository.jpa_interface;

import org.springframework.data.repository.ListCrudRepository;

import com.example.sales_system.repository.jpa_entities.StockItemEntity;

import java.util.Optional;

public interface StockJPA extends ListCrudRepository<StockItemEntity,Long> {
    Optional<StockItemEntity> findByProductId(long id);
}