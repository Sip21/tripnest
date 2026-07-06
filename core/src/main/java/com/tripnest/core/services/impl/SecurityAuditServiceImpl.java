package com.tripnest.core.services.impl;

import com.tripnest.core.models.AccessReport;
import com.tripnest.core.services.SecurityAuditService;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Session;

@Component(service = SecurityAuditService.class)
public class SecurityAuditServiceImpl implements SecurityAuditService {

    @Override
    public AccessReport checkAccess(ResourceResolver resolver, String path) {

        AccessReport report = new AccessReport();
        String userId = resolver.getUserID();

        report.currentUser = userId;
        report.userType = getUserType(userId);

        Session session = resolver.adaptTo(Session.class);

        if (session == null || path == null || path.isEmpty()) {
            // No session or no path -> deny everything, simple and safe
            report.canRead = false;
            report.canModify = false;
            report.canDelete = false;
            return report;
        }

        try {
            report.canRead = session.hasPermission(path, "read");
            report.canModify = session.hasPermission(
                    path,
                    Session.ACTION_ADD_NODE + "," + Session.ACTION_SET_PROPERTY);
            report.canDelete = session.hasPermission(path, "remove");
        } catch (Exception e) {
            // If anything goes wrong, fail safe -> deny
            report.canRead = false;
            report.canModify = false;
            report.canDelete = false;
        }

        return report;
    }

    private String getUserType(String userId) {
        if ("anonymous".equals(userId)) {
            return "Anonymous";
        } else if ("admin".equals(userId)) {
            return "Admin";
        } else if (userId != null && userId.contains("-service")) {
            return "Service User";
        } else {
            return "Normal User";
        }
    }
}