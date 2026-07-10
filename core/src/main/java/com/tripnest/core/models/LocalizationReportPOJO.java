package com.tripnest.core.models;

public class LocalizationReportPOJO {

    private String language;
    private String country;
    private boolean liveCopy;
    private String dictionary;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isLiveCopy() {
        return liveCopy;
    }

    public void setLiveCopy(boolean liveCopy) {
        this.liveCopy = liveCopy;
    }

    public String getDictionary() {
        return dictionary;
    }

    public void setDictionary(String dictionary) {
        this.dictionary = dictionary;
    }

}
