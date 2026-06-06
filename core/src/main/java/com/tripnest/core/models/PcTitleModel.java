package com.tripnest.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.tripnest.core.services.PcTitleService;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PcTitleModel {

    // @SlingObject
    // private Resource resource;

    // @ValueMapValue
    // @Default(values = "Untitled Article")
    // private String title;

    // @ValueMapValue
    // private String description;

    // public String getTitle() {
    // return title;
    // }

    // public String getDescription() {
    // return description;
    // }

    // public String getCurrentPath() {
    // return resource.getPath();
    // }

    // public String getName() {
    // return resource.getName();
    // }

    // public ResourceResolver getResourceResolver() {
    // return resource.getResourceResolver();
    // }

    // public ResourceMetadata getResourceMetadata() {
    // return resource.getResourceMetadata();
    // }

    // public ValueMap getValueMap() {
    // return resource.getValueMap();
    // }

    // public Map<String, Object> getDetails() {
    // Map<String, Object> details = new HashMap<>();
    // details.put("name", resource.getName());
    // details.put("path", resource.getPath());
    // details.put("resourceResolver", resource.getResourceResolver());
    // details.put("resourceMetadata", resource.getResourceMetadata());
    // details.put("valueMap", resource.getValueMap());
    // details.put("resourceType", resource.getResourceType());
    // return details;
    // }

    // @ChildResource
    // private List<Resource> fruits;

    // public List<String> getFruits() {
    // List<String> fruitName = new ArrayList<>();
    // if (fruits != null) {
    // for (Resource fruit : fruits) {
    // String name = fruit.getValueMap().get("fruitName", String.class);
    // fruitName.add(name);
    // }
    // }
    // return fruitName;
    // }

    @Self
    private SlingHttpServletRequest request;

    public String getPath() {
        return request.getPathInfo();
    }

    @OSGiService
    private PcTitleService pcTitleservice;

    public String getWelcomeMessage() {
        return pcTitleservice != null ? pcTitleservice.getWelcomeMessage() : "";
    }

}
