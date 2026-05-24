package mazenscode.customermanagementapplication.service;

import mazenscode.customermanagementapplication.repository.CustomerDAO;
import mazenscode.customermanagementapplication.entity.Customers;
import mazenscode.customermanagementapplication.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    @Override
    public List<Customers> getAllCustomers() {

        return customerDAO.findAll();
    }

    @Override
    public Customers getCustomerByID(Integer id) {

        // check if the customer is there or not
        Optional<Customers> result = customerDAO.findById(id);

        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new CustomerNotFoundException("Customer Not Found");
        }
    }

    @Override
    public Customers saveCustomer(Customers customer) {

        return customerDAO.save(customer);
    }

    @Override
    public void deleteCustomerByID(Integer id) {

        customerDAO.deleteById(id);
    }
}
