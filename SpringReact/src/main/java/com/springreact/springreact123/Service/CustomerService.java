package com.springreact.springreact123.Service;

import com.springreact.springreact123.DTO.CustomerDTO;
import com.springreact.springreact123.DTO.CustomerSaveDTO;
import com.springreact.springreact123.DTO.CustomerUpdateDTO;

import java.util.List;


public interface CustomerService {
    String addCustomer(CustomerSaveDTO customerSaveDTO);

    List<CustomerDTO> getAllCustomer();

    String updateCustomers(CustomerUpdateDTO customerUpdateDTO);

    boolean deleteCustomer(int id);
}
