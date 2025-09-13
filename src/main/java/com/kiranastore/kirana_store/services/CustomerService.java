package com.kiranastore.kirana_store.services;

import com.kiranastore.kirana_store.dtos.CustomerRequest;
import com.kiranastore.kirana_store.dtos.CustomerResponse;
import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse getCustomerById(Long id);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse updateCustomer(Long id, CustomerRequest request);
    void deleteCustomer(Long id);
}
