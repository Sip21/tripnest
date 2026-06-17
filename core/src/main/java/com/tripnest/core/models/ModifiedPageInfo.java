package com.tripnest.core.models;

public class ModifiedPageInfo {
    private String path;
    private String modifiedDate;

    public ModifiedPageInfo(String path, String modifiedDate) {
        this.path = path;
        this.modifiedDate = modifiedDate;
    }

    public String getPath() {
        return path;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }
}
