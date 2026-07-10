package com.tripnest.core.services;

import com.day.cq.wcm.api.Page;
import com.tripnest.core.models.LocalizationReportPOJO;

public interface LocalizationService {
    LocalizationReportPOJO getLocalizationReport(Page currentPage);
}
