/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sabre
 */
public class FacadeTester {
    
    public static void main(String[] args) {
        
        UserFacade facade = new UserFacade(Persistence.createEntityManagerFactory("AngSeedServerPU"));
        
        facade.persistUsers();
        
    }
    
    
}
