package com.tripnest.core.services.impl;

import java.util.Locale;
import com.day.cq.wcm.api.WCMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.wcm.api.LanguageManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.msm.api.LiveRelationship;
import com.day.cq.wcm.msm.api.LiveRelationshipManager;
import com.tripnest.core.models.LocalizationReportPOJO;
import com.tripnest.core.services.LocalizationService;

@Component(service = LocalizationService.class)
public class LocalizationServiceImpl implements LocalizationService {

    private static final Logger LOG = LoggerFactory.getLogger(LocalizationServiceImpl.class);

    @Reference
    private LiveRelationshipManager liveRelationshipManager;

    @Reference
    private LanguageManager languageManager;

    @Override
    public LocalizationReportPOJO getLocalizationReport(Page currentPage) {

        LocalizationReportPOJO report = new LocalizationReportPOJO();
        if (currentPage == null) {
            return report;
        }

        Locale locale = currentPage.getLanguage(true);

        Page languageRoot = languageManager.getLanguageRoot(currentPage.getContentResource());
        String localeString = languageRoot.getProperties().get("jcr:language", String.class);
        String[] parts = localeString.split("_");
        report.setLanguage(parts[0]);
        report.setCountry(parts[1].toUpperCase());
        report.setDictionary(locale.getLanguage());

        try {
            LiveRelationship relationship = liveRelationshipManager.getLiveRelationship(
                    currentPage.adaptTo(Resource.class), false);

            report.setLiveCopy(relationship != null);
        } catch (WCMException e) {
            LOG.error("Unable to determine Live Copy for {}", currentPage.getPath(), e);
            report.setLiveCopy(false);
        }
        return report;
    }
}
