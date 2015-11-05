/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.User;
import java.util.List;

/**
 *
 * @author simon
 */
public class UserFacadeTest {
    public static void main(String[] args) {
        UserFacade uf = new UserFacade();
        List<User> us = uf.getUsers();
        
        for (User u : us ) {
            System.out.println(u.getUserName());
        }
        
    }
}
