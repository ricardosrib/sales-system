package com.example.sales_system.repository.interfaceJPA;

import com.example.sales_system.repository.entitiesJPA.BudgetEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface BudgetJPA extends ListCrudRepository<BudgetEntity,Long> {}