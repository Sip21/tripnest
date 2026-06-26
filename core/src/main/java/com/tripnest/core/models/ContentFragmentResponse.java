package com.tripnest.core.models;

import java.util.List;

public class ContentFragmentResponse {

    private String fragmentName;
    private List<ContentElementResponse> elements;

    public ContentFragmentResponse(String fragmentName, List<ContentElementResponse> elements) {
        this.fragmentName = fragmentName;
        this.elements = elements;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    public List<ContentElementResponse> getElements() {
        return elements;
    }

    public void setElements(List<ContentElementResponse> elements) {
        this.elements = elements;
    }

}
