package controller;

import java.util.ArrayList;
import model.entities.Customer;
import model.data.CustomerData;
import model.entities.Clerk;

public class CustomerController {
    private CustomerData customerData = new CustomerData();

    public String insertCustomer(Customer customer) {
        if (customerData.findCustomerById(customer.getId()) == null) {
            customerData.insertCustomer(customer);
            return "Cliente insertado con éxito.";
        }
        return "No se insertó cliente porque este ya existe.";
    }

    public Customer searchCustomer(String query) {
        for (Customer c : customerData.getAllCustomers()) {
            if (c.getId().equalsIgnoreCase(query) || c.getName().toLowerCase().contains(query.toLowerCase())) {
                return c;
            }
        }
        return null;
    }

    public String updateCustomer(Customer customer) {
        if (customerData.findCustomerById(customer.getId()) != null) {
            customerData.updateCustomer(customer);
            return "Cliente actualizado con éxito.";
        }
        return "Error: El cliente no existe.";
    }

    public String deleteCustomer(Customer customer) {
        if (customerData.findCustomerById(customer.getId()) != null) {
            customerData.deleteCustomer(customer.getId());
            return "Cliente eliminado con éxito.";
        }
        return "Error: El cliente no existe.";
    }

    public ArrayList<Customer> getAllCustomers() {
        return customerData.getAllCustomers();
    }
    
 

}