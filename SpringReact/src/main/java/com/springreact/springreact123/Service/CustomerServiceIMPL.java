package com.springreact.springreact123.Service;

import com.springreact.springreact123.CustomerRepository.CustomerRepo;
import com.springreact.springreact123.DTO.CustomerDTO;
import com.springreact.springreact123.DTO.CustomerSaveDTO;
import com.springreact.springreact123.DTO.CustomerUpdateDTO;
import com.springreact.springreact123.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService{

    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public String addCustomer(CustomerSaveDTO customerSaveDTO) {
        Customer customer = new Customer(
                customerSaveDTO.getCustomerName(),
                customerSaveDTO.getCustomerAddress(),
                customerSaveDTO.getMobile()
        );
        customerRepo.save(customer);
        return customer.getCustomerName();
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> getCustomers = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for(Customer a :getCustomers){
            CustomerDTO customerDTO = new CustomerDTO(

                    a.getCustomerId(),
                    a.getCustomerName(),
                    a.getCustomerAddress(),
                    a.getMobile()
            );
            customerDTOList.add(customerDTO);
        }

        return customerDTOList;
    }

    @Override
    public String updateCustomers(CustomerUpdateDTO customerUpdateDTO) {
        if(customerRepo.existsById(customerUpdateDTO.getCustomerId()))
        {
            Customer customer = customerRepo.getById(customerUpdateDTO.getCustomerId());

            customer.setCustomerName(customerUpdateDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
            customer.setMobile(customerUpdateDTO.getMobile());
            customerRepo.save(customer);
        }
        else {
            System.out.println("Customer ID not exist");
        }
        return null;
    }

    @Override
    public boolean deleteCustomer(int id) {
        if(customerRepo.existsById(id))
        {
            customerRepo.deleteById(id);
        }
        else {
            System.out.println("Customer is not found");
        }
        return true;
    }
}
