package model.data;

import java.util.ArrayList;
import model.data.Customer;

public class CustomerData {

    //Este arrelgo guardará a todos los clientes
    //Simulando una base de dato
    ArrayList<Customer> customers = new ArrayList<>();

    public void insertCustomer(Customer customer){

        customers.add(customer);

    }

    public ArrayList<Customer> getAllCustomers(){


        return customers;
    }


    public Customer findCustomerById(String id){
        Customer customerToReturn = null;

        for (Customer customer : customers) {

            if (customer.getId().equals(id)){

                customerToReturn= customer;
            }

        }

        return customerToReturn;
    }
    public void updateCustomer(Customer updatedCustomer) {
        for (int i = 0; i < customers.size(); i++) {
            // Buscamos la posición donde el ID coincida
            if (customers.get(i).getId().equals(updatedCustomer.getId())) {
                customers.set(i, updatedCustomer);
                return;
            }
        }
    }

    public void deleteCustomer(String id) {
        Customer customer = findCustomerById(id);
        if (customer != null) {
            customers.remove(customer);
        }
    }

}
