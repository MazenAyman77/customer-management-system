package mazenscode.customermanagementapplication.controller;

import mazenscode.customermanagementapplication.entity.Customers;
import mazenscode.customermanagementapplication.exceptions.CustomerNotFoundException;
import mazenscode.customermanagementapplication.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class CustomerController {

    private final CustomerService customerService;
    private final ObjectMapper objectMapper;

    public CustomerController(CustomerService customerService, ObjectMapper objectMapper) {

        this.customerService = customerService;
        this.objectMapper = objectMapper;
    }

    // getting all customers
    @GetMapping("/customers")
    public List<Customers> getAllEmployees(){

        return customerService.getAllCustomers();
    }

    // getting customer by id
    @GetMapping("/customers/{id}")
    public Customers getEmployeeByID(@PathVariable int id){

        Customers theCustomer = customerService.getCustomerByID(id);

        if(theCustomer == null){
            throw new CustomerNotFoundException("this customer is not found.........");
        }

        else{return theCustomer;}

    }

    // creating a new customer
    @PostMapping("/customers")
    public Customers saveEmployee(@RequestBody Customers customer){

        return customerService.saveCustomer(customer);
    }

    // partial updating an existing customer
    @PutMapping("/customers/{id}")
    public Customers patchEmployee(@PathVariable int id, @RequestBody Customers updatedCustomer){

        Customers existingCustomer =
                customerService.getCustomerByID(id);

        if (existingCustomer == null) {

            throw new CustomerNotFoundException(
                    "Customer not found"
            );
        }

        existingCustomer.setName(
                updatedCustomer.getName()
        );

        existingCustomer.setEmail(
                updatedCustomer.getEmail()
        );

        existingCustomer.setPhone(
                updatedCustomer.getPhone()
        );

        return customerService.saveCustomer(
                existingCustomer
        );

    }

    @DeleteMapping("/customers/{id}")
    public String deleteEmployee(@PathVariable int id){

        Customers theEmployee = customerService.getCustomerByID(id);

        if(theEmployee == null){
            throw new CustomerNotFoundException("this employee is not found to be updated.........");
        }
        else{
            customerService.deleteCustomerByID(id);
            return "successfully deleted employee: " + id ;
        }
    }

}
