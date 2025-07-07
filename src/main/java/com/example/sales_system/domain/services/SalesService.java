package com.example.sales_system.domain.services;

import com.example.sales_system.domain.model.*;
import com.example.sales_system.repository.repositoryInterface.IBudgetRepository;
import com.example.sales_system.repository.repositoryInterface.ICustomerRepository;
import com.example.sales_system.repository.repositoryInterface.IStockRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalesService {
    private IBudgetRepository budgets;
    private IStockRepository stock;
    private ICustomerRepository customers;
    private StockService stockService;

    public SalesService(IBudgetRepository budgets,
                        IStockRepository stock,
                        StockService stockService,
                        ICustomerRepository customers) {
        this.budgets = budgets;
        this.stock = stock;
        this.stockService = stockService;
        this.customers = customers;
    }

    public List<ProductModel> availableProducts() {
        return stock.findAllWithStock();
    }

    public BudgetModel getBudgetById(long id) {
        return this.budgets.findById(id);
    }

    public BudgetModel createBudget(long customerId, OrderModel order) {
        System.out.println("Creating budget for customer ID: " + customerId + " with order: " + order);

        var newBudget = new BudgetModel();
        newBudget.addOrderItems(order);

        // Check if customer exists
        CustomerModel customer = this.customers.findById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        // Associate customer with budget
        newBudget.setCustomer(customer);
        newBudget.setBudgetDate(LocalDate.now());

        // Calculate item cost
        double itemCost = newBudget.getItems().stream()
                .mapToDouble(it -> it.getProduct().getUnitPrice() * it.getQuantity())
                .sum();
        newBudget.setItemCost(itemCost);

        return this.budgets.register(newBudget);
    }

    public BudgetModel confirmBudget(long id) {
        var budget = this.budgets.findById(id);
        if (budget == null) {
            throw new IllegalArgumentException("Budget not found: " + id);
        }

        if (budget.isFinalized()) {
            throw new IllegalArgumentException("Budget already confirmed: " + id);
        }

        LocalDate expiration = budget.getBudgetDate().plusDays(21);
        if (LocalDate.now().isAfter(expiration)) {
            throw new IllegalArgumentException("Budget expired on: " + expiration.toString());
        }

        // Check stock availability for all items
        for (OrderItemModel item : budget.getItems()) {
            int available = stock.getStockQuantity(item.getProduct().getId());
            if (available < item.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + item.getProduct().getDescription() +
                        ". Available: " + available + ", Requested: " + item.getQuantity());
            }
        }

        // Deduct stock if all items are available
        for (OrderItemModel item : budget.getItems()) {
            int available = stock.getStockQuantity(item.getProduct().getId());
            if (available >= item.getQuantity()) {
                stockService.deductStock(item.getProduct().getId(), item.getQuantity());
            }
        }

        budget.setConfirmationDate(LocalDate.now());
        budget.finalizeBudget();
        budgets.save(budget);

        return budget;
    }
}
