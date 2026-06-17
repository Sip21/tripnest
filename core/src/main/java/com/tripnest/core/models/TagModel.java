package com.tripnest.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.TagService;

@Model(adaptables = Resource.class)
public class TagModel {
    private static final Logger LOG = LoggerFactory.getLogger(TagModel.class);

    @OSGiService
    private TagService service;

    private List<String> pagesWithTag;

    @PostConstruct
    protected void init() {
        LOG.info("TagModel init called");
        pagesWithTag = service.getPagesWithTag();
    }

    public List<String> getPagesWithTag() {
        return pagesWithTag;
    }

}
