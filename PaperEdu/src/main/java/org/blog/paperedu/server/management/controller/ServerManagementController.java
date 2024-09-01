package org.blog.paperedu.server.management.controller;

import org.blog.paperedu.PaperEdu;
import org.blog.paperedu.server.management.model.AnnouncementRunnable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ServerManagementController {
    private final static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    public ServerManagementController() {
        registerServerSchedulerTask();
    }

    private void registerServerSchedulerTask(){
        scheduledExecutorService.scheduleAtFixedRate(new AnnouncementRunnable(), 10, 10, TimeUnit.MINUTES);
    }
}
