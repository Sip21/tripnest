package com.tripnest.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.HashMap;
import java.util.Map;

@Model(adaptables = Resource.class)
public class TravelCardModel {

    public Map<String, String> getCardAttributes() {

        Map<String, String> attrs = new HashMap<>();

        attrs.put("class", "travel-card");
        attrs.put("id", "venice-card");
        attrs.put("title", "Explore Venice");
        attrs.put("data-country", "Italy");

        return attrs;
    }
}