/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.data.CustomerData;
import model.entities.Customer;

/**
 *
 * @author adrie
 */
public class CustomerController {

    CustomerData customerData = new CustomerData(); //como es clase() no objeto
    //intermediario entre Vista y modelo
    //va a llamar el método en Data para no acceder directamenta a la vista 

    public String insertCustomer(Customer customer) {
        String result = " ";
        // si hay reglas de negocio, se aplican acá
        //antes de insertar cliente en Data
        if (customerData.findCustomerById(customer.getId()) == null) {
             customerData.insertCustomer(customer);
            result = "Cliente insertado con èxito";
            
        } else {
            result = "No se insertò el cliente porque este ya existe en la base de datos";
        }
        return result;
       
    }
    
    public Customer findCustomerById(String id) {
        return customerData.findCustomerById(id);
    }

    public ArrayList<Customer> getAllCustomers() {
        return customerData.getAllCustomers();

    }

    public String updateCustomer(Customer customer) {
    if (customerData.findCustomerById(customer.getId()) != null) {
        customerData.updateCustomer(customer);
        return "Cliente actualizado con éxito.";
    } else {
        return "Error: El cliente no existe, no se puede actualizar.";
    }
}

    //tarea
    public String deleteCustomer(Customer customer) {
        if (customerData.findCustomerById(customer.getId()) != null) {
            customerData.deleteCustomer(customer.getId());
            return "Cliente eliminado con éxito.";
        } else {
            return "Error: El cliente no existe, no se puede eliminar.";
        }
    }

}
