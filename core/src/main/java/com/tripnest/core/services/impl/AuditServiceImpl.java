package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Component;

import com.tripnest.core.services.AuditService;

@Component(service = AuditService.class, immediate = true)
public class AuditServiceImpl implements AuditService {

    @Override
    public String getAuditMessage() {
        return "Audit Entry Created";
    }

}
