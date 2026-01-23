package model.data;


import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import model.entities.Customer;

import java.io.File;


public class CustomerData {

    private final String fileName = "customers.txt";
    private ArrayList<Customer> customers;

    public CustomerData() {
        this.customers = new ArrayList<>();
        loadFromFile();
    }


    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ";");
                if (st.countTokens() >= 3) {
                    String id = st.nextToken();
                    String name = st.nextToken();
                    boolean disability = Boolean.parseBoolean(st.nextToken());
                    customers.add(new Customer(id, name, disability));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Customer c : customers) {
                pw.println(c.getId() + ";" + c.getName() + ";" + c.isDisabilityPresented());
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }


    public void insertCustomer(Customer customer) {
        customers.add(customer);
        saveToFile(); 
    }

    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }

    public Customer findCustomerById(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    public void updateCustomer(Customer updatedCustomer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(updatedCustomer.getId())) {
                customers.set(i, updatedCustomer);
                saveToFile(); 
                return;
            }
        }
    }

    public void deleteCustomer(String id) {
        Customer customer = findCustomerById(id);
        if (customer != null) {
            customers.remove(customer);
            saveToFile(); 
        }
    }
}