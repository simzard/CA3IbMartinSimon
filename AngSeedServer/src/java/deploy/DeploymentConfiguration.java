/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deploy;

import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import utils.RatesUpdater;

/**
 *
 * @author simon
 */
public class DeploymentConfiguration implements ServletContextListener {

    public static String PU_NAME = "AngSeedServerPU";

    public void contextInitialized(ServletContextEvent sce) {
       
        
        Map<String, String> env = System.getenv();
//If we are running in the OPENSHIFT environment change the pu-name
        if (env.keySet().contains("OPENSHIFT_MYSQL_DB_HOST")) {
            PU_NAME = "pu_OPENSHIFT";
            RatesUpdater x = new RatesUpdater();
            x.JobScheduler();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
