/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.User;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sabre
 */
public class FacadeTester {

    public static void main(String[] args) {

        UserFacade facade = new UserFacade(Persistence.createEntityManagerFactory("AngSeedServerPU"));
//        UserFacade facade = new UserFacade();
//        System.out.println(facade.doesUserExist("adminLars"));
         facade.persistUsers();
//  
//        
//        User testuser = facade.getUserByUserId("adminLars");
//        
//        System.out.println(testuser.getUserName());
//        System.out.println(testuser.getRoles().get(0));
//        
//        System.out.println("---------------------------");
//        
//      List<String> finn = facade.authenticateUser("adminLars", "test");
//        System.out.println(finn.get(0));
//        ExchangeRateFacade xf = new ExchangeRateFacade();
//
//        xf.fetchExchangeRates();

    }

}


