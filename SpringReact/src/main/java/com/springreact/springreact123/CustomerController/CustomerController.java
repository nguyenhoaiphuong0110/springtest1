package com.springreact.springreact123.CustomerController;


import com.springreact.springreact123.DTO.CustomerDTO;
import com.springreact.springreact123.DTO.CustomerSaveDTO;
import com.springreact.springreact123.DTO.CustomerUpdateDTO;
import com.springreact.springreact123.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")

public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(path="/save")
    public String saveCustomer(@RequestBody CustomerSaveDTO customerSaveDTO){
        String id = customerService.addCustomer(customerSaveDTO);
        return id;
    }
    @GetMapping(path = "/getAllCustomer")
    public List<CustomerDTO> getAllCustomer()
    {
        List<CustomerDTO>allCustomers = customerService.getAllCustomer();
        return allCustomers;
    }

    @PostMapping(path = "/update")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO)
    {
        String id = customerService.updateCustomers(customerUpdateDTO);
        return id;
    }
    @DeleteMapping(path = "/deleteCustomer/{id}")
    public String deleteCutomer(@PathVariable(value = "id") int id){
        boolean deletecustomer = customerService.deleteCustomer(id);
        return "delete";
    }
}
