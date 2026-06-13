package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Component;

import com.tripnest.core.services.SchedulerInfoService;

@Component(service = SchedulerInfoService.class, immediate = true)
public class SchedulerInfoServiceImpl implements SchedulerInfoService {

    private String lastRunTime;

    @Override
    public void setLastRunTime(String time) {
        this.lastRunTime = time;
    }

    @Override
    public String getLastRunTime() {
        return lastRunTime;
    }

}
