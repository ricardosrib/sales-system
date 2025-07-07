package com.example.sales_system.repository.repositoryInterface;

import com.example.sales_system.domain.model.CustomerModel;

public interface ICustomerRepository {
    CustomerModel findById(long id);
}
