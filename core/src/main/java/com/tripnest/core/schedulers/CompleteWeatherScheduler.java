package com.tripnest.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.config.CompleteWeatherConfig;
import com.tripnest.core.models.WeatherNewPOJO;
import com.tripnest.core.models.WeatherPOJO;
import com.tripnest.core.services.CompleteWeatherService;
import com.tripnest.core.services.WeatherService;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = CompleteWeatherConfig.class)
public class CompleteWeatherScheduler implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(CompleteWeatherScheduler.class);

    private static final String SCHEDULER_ID = "CompleteWeatherScheduler";

    @Reference
    private CompleteWeatherService weatherService;

    private String schedulerExpression;

    @Reference
    private Scheduler scheduler;

    @Activate
    @Modified
    protected void activate(CompleteWeatherConfig config) {
        this.schedulerExpression = config.schedulerExpression();
        removeScheduler();
        // addScheduler();
        LOG.info("Activate Method Called - CompleteWeatherScheduler");
    }

    private void addScheduler() {
        ScheduleOptions options = scheduler.EXPR(schedulerExpression);
        options.name(SCHEDULER_ID);
        options.canRunConcurrently(false);
        scheduler.schedule(this, options);
        LOG.info("Added Method Called - CompleteWeatherScheduler");
    }

    @Deactivate
    protected void deactivate() {
        removeScheduler();
        LOG.info("Deactivate Method Called - CompleteWeatherScheduler");
    }

    private void removeScheduler() {
        scheduler.unschedule(SCHEDULER_ID);
        LOG.info("Remove Method Called - CompleteWeatherScheduler");
    }

    @Override
    public void run() {
        LOG.info("Weather Scheduler Started");
        try {
            WeatherNewPOJO weather = weatherService.callWeatherApi();
            weatherService.storeLatestWeather(weather);
            LOG.info("Weather Data Updated Successfully");
            LOG.info("Running Method Called - CompleteWeatherScheduler");
        } catch (Exception e) {
            LOG.error("Weather Scheduler Failed", e);
        }
    }

}
