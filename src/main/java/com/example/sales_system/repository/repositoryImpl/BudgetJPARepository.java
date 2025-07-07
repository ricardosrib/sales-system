package com.example.sales_system.repository.repositoryImpl;

import com.example.sales_system.domain.model.BudgetModel;
import com.example.sales_system.repository.entitiesJPA.BudgetEntity;
import com.example.sales_system.repository.interfaceJPA.BudgetJPA;
import com.example.sales_system.repository.repositoryInterface.IBudgetRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class BudgetJPARepository implements IBudgetRepository {
    private BudgetJPA budgetRepository;

    public BudgetJPARepository(BudgetJPA repository) {
        this.budgetRepository = repository;
    }

    @Override
    public List<BudgetModel> findAll() {
        var budgets = budgetRepository.findAll();
        return budgets.stream()
                .map(BudgetEntity::toBudgetModel)
                .toList();
    }

    @Override
    public BudgetModel register(BudgetModel budget) {
        var budgetJpa = BudgetEntity.fromBudgetModel(budget);
        var savedBudget = budgetRepository.save(budgetJpa);
        return BudgetEntity.toBudgetModel(savedBudget);
    }

    @Override
    public BudgetModel findById(long id) {
        var budget = budgetRepository.findById(id).orElse(null);
        return (budget == null) ? null : BudgetEntity.toBudgetModel(budget);
    }

    @Override
    public void save(BudgetModel budget) {
        var budgetJpa = BudgetEntity.fromBudgetModel(budget);
        budgetRepository.save(budgetJpa);
    }
}
