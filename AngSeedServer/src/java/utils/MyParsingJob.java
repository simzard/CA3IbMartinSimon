/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class MyParsingJob implements Runnable {

    private ScheduledExecutorService scheduler;

    public void BGJobManager() {

        scheduler = Executors.newSingleThreadScheduledExecutor();
        Calendar cal = Calendar.getInstance();

        int currentTimeHour = cal.get(Calendar.HOUR);
        int currentTimeMinute = cal.get(Calendar.MINUTE);

        currentTimeHour = Calendar.AM == 0 ? currentTimeHour + 12 : currentTimeHour;
        int startTime = 24 - (currentTimeHour - 8);
        System.out.println("StartTime: " + startTime);
        startTime = cal.get(Calendar.SECOND);

        System.out.println(currentTimeHour);
        scheduler.scheduleAtFixedRate(new MyParsingJob(), startTime, 1, TimeUnit.HOURS);
    }

    public MyParsingJob() {
    }

    @Override
    public void run() {
    }
}
//
//class tester {
//
//    public static void main(String[] args) {
//        YourParsingJob x = new YourParsingJob();
//        x.BGJobManager();
//
//    }
//}
