package com.tripnest.core.services;

import java.util.List;

import com.tripnest.core.models.AuditPOJO;

public interface AuditQueryService {

    List<AuditPOJO> getRecentlyModifiedPages();
}
