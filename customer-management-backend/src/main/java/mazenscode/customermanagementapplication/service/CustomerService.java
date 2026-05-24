package mazenscode.customermanagementapplication.service;

import mazenscode.customermanagementapplication.entity.Customers;

import java.util.List;

// service layer contains the business logic
public interface CustomerService {

    List<Customers> getAllCustomers();
    Customers getCustomerByID(Integer id);
    Customers saveCustomer(Customers customer);
    void deleteCustomerByID(Integer id);

}