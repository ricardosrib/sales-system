package com.example.sales_system.repository.repositoryImpl;

import com.example.sales_system.domain.model.CustomerModel;
import com.example.sales_system.repository.entitiesJPA.CustomerEntity;
import com.example.sales_system.repository.interfaceJPA.CustomerJPA;
import com.example.sales_system.repository.repositoryInterface.ICustomerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class CustomerJPARepository implements ICustomerRepository {
    private CustomerJPA customerRepository;

    public CustomerJPARepository(CustomerJPA customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerModel findById(long id) {
        CustomerEntity customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return null;
        } else {
            return CustomerEntity.toCustomerModel(customer);
        }
    }
}
