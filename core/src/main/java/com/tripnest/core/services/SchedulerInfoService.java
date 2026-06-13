package com.tripnest.core.services;

public interface SchedulerInfoService {
    void setLastRunTime(String time);

    String getLastRunTime();
}
