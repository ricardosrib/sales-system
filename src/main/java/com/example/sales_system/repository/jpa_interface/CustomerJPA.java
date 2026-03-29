package com.example.sales_system.repository.jpa_interface;

import org.springframework.data.repository.ListCrudRepository;

import com.example.sales_system.repository.jpa_entities.CustomerEntity;

public interface CustomerJPA extends ListCrudRepository<CustomerEntity, Long> {}