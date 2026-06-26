package com.tripnest.core.models;

public class ContentElementResponse {

    private String elementName;
    private String elementValue;

    public ContentElementResponse(String elementName, String elementValue) {
        this.elementName = elementName;
        this.elementValue = elementValue;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementValue() {
        return elementValue;
    }

    public void setElementValue(String elementValue) {
        this.elementValue = elementValue;
    }
}
