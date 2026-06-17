package com.tripnest.core.models;

public class AuditPOJO {

    private String path;
    private String modifiedBy;
    private String modifiedDate;

    public AuditPOJO(String path, String modifiedDate, String modifiedBy) {
        this.path = path;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    public String getPath() {
        return path;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

}
