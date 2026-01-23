package controller;

import model.data.CustomerData;
import model.entities.Customer;

import java.util.ArrayList;
//Hola mundo feo

public class CustomerController {

    CustomerData customerData = new CustomerData();

    public String insertCustomer(Customer customer) {
        String result;

        if(customerData.findCustomerById(customer.getId())==null){
            customerData.insertCustomer(customer);
            result="Cliente insertado con éxito";

        }
        else{
            result="No se insertó cliente porque este ya existe";
        }

        //Si hay reglas de negocio, se aplican acá
        //anter de insertar cliente en Data

        return result;
    }

    public String updateCustomer(Customer customer) {
        if (customerData.findCustomerById(customer.getId()) != null) {
            customerData.updateCustomer(customer);
            return "Cliente actualizado con éxito.";
        } else {
            return "Error: El cliente no existe, no se puede actualizar.";
        }
    }

    public String deleteCustomer(Customer customer) {
        if (customerData.findCustomerById(customer.getId()) != null) {
            customerData.deleteCustomer(customer.getId());
            return "Cliente eliminado con éxito.";
        } else {
            return "Error: El cliente no existe, no se puede eliminar.";
        }
    }

    public ArrayList<Customer> getAllCustomers() {

        return customerData.getAllCustomers();
    }


}
