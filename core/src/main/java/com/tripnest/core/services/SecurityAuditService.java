package com.tripnest.core.services;

import org.apache.sling.api.resource.ResourceResolver;

import com.tripnest.core.models.AccessReport;

public interface SecurityAuditService {
    AccessReport checkAccess(ResourceResolver resolver, String path);
}