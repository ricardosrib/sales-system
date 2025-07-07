package com.example.sales_system.repository.interfaceJPA;

import com.example.sales_system.repository.entitiesJPA.CustomerEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface CustomerJPA extends ListCrudRepository<CustomerEntity, Long> {}