/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import model.data.AdministratorData;
import model.entities.Customer;
import model.entities.User;
import model.entities.UserOperations;

import java.util.ArrayList;

/**
 *
 * @author Lab07 Guiselle López 
 */
public class AdministratorController implements UserOperations {
    
    AdministratorData administradorData = new AdministratorData();


    @Override
    public User searchUser(String identification) {
        return null;
    }

    @Override
    public User searchUser(User user) {
        return null;
    }

    @Override
    public ArrayList<User> sortUsers(Customer[] allUsers) {
        return null;
    }

    @Override
    public ArrayList<User> sortUsers(String identification, User[] allUsers) {
        return null;
    }
}
