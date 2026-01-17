/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.data;

import java.util.ArrayList;
import model.entities.Customer;

/**
 *
 * @author adrie
 */
public class CustomerData {

    //este arreglo dinámico gurdara a todos los clientes del parqueo 
    //simulando una base de datos.
    ArrayList<Customer> customers = new ArrayList<>(); //intanciación  

    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    public ArrayList<Customer> getAllCustomers() {
     return customers;
    }

    public Customer findCustomerById(String id) {
        
        Customer customerToReturn = null;
        for (Customer customer : customers) { // para cada cliente que se encuentre en la lista haga...  .length para estaticos  .size();
            if (customer.getId().equals(id)) {
                customerToReturn = customer;
            }
        }
        return customerToReturn;
    }
    //tarea
    public void updateCustomer(Customer updatedCustomer) {
    for (Customer customer : customers) {
        if (customer.getId().equals(updatedCustomer.getId())) {

            customer.setName(updatedCustomer.getName());
            customer.setDisabilityPresented(updatedCustomer.isDisabilityPresented());

            return;
        }
    }
}
    //tarea
    public void deleteCustomer(String id) {
        Customer customer = findCustomerById(id);
        if (customer != null) {
            customers.remove(customer);
        }
    }
}

