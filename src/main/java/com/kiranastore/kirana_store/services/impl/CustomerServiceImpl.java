package com.kiranastore.kirana_store.services.impl;

import com.kiranastore.kirana_store.dtos.CustomerRequest;
import com.kiranastore.kirana_store.dtos.CustomerResponse;
import com.kiranastore.kirana_store.entities.Customer;
import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.exception.ResourceNotFoundException;
import com.kiranastore.kirana_store.repositories.CustomerRepository;
import com.kiranastore.kirana_store.repositories.KiranaOwnerRepository;
import com.kiranastore.kirana_store.services.CustomerService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final KiranaOwnerRepository ownerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, KiranaOwnerRepository ownerRepository) {
        this.customerRepository = customerRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer entity = mapToEntity(request);
        return mapToResponse(customerRepository.save(entity));
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer entity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return mapToResponse(entity);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer entity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        entity.setName(request.getName());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setAddress(request.getAddress());

        return mapToResponse(customerRepository.save(entity));
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer entity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(entity);
    }

    private Customer mapToEntity(CustomerRequest request) {
    	KiranaOwner owner = ownerRepository.findById(request.getOwnerId()).
    			orElseThrow(() -> new ResourceNotFoundException("Owner not found with id: " + request.getOwnerId()));
        Customer c = new Customer();
        c.setName(request.getName());
        c.setPhone(request.getPhone());
        c.setEmail(request.getEmail());
        c.setAddress(request.getAddress());
        c.setOwner(owner); // 🔹 set owner
        return c;
    }
    
    private CustomerResponse mapToResponse(Customer entity) {
        CustomerResponse dto = new CustomerResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        dto.setOwnerId(entity.getOwner().getId());
        dto.setOwnerName(entity.getOwner().getName());
        return dto;
    }

}

