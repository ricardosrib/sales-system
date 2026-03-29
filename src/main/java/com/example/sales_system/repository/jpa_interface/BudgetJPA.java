package com.example.sales_system.repository.jpa_interface;

import org.springframework.data.repository.ListCrudRepository;

import com.example.sales_system.repository.jpa_entities.BudgetEntity;

public interface BudgetJPA extends ListCrudRepository<BudgetEntity,Long> {}