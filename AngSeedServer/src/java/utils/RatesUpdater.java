/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import facades.ExchangeRateFacade;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RatesUpdater implements Runnable {

    ExchangeRateFacade xf = new ExchangeRateFacade();
    
    private ScheduledExecutorService scheduler;

    public void JobScheduler() {

        scheduler = Executors.newSingleThreadScheduledExecutor();
        Calendar cal = Calendar.getInstance();

        int currentTimeHour = cal.get(Calendar.HOUR);
        int currentTimeMinute = cal.get(Calendar.MINUTE);

        currentTimeHour = Calendar.AM == 0 ? currentTimeHour + 12 : currentTimeHour;
        int startTime = 24 - (currentTimeHour - 8);
        
        startTime = cal.get(Calendar.SECOND);

        
       
        scheduler.scheduleAtFixedRate(new RatesUpdater(), startTime, 24, TimeUnit.HOURS);
    }

   
    @Override
    public void run() {
        xf.fetchExchangeRates();
    }




   
}
