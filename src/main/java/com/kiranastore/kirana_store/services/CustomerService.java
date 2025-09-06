package com.kiranastore.kirana_store.services;

import java.util.List;

import com.kiranastore.kirana_store.dtos.CustomerRequest;
import com.kiranastore.kirana_store.dtos.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse getCustomerById(Long id);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse updateCustomer(Long id, CustomerRequest request);
    void deleteCustomer(Long id);
}
