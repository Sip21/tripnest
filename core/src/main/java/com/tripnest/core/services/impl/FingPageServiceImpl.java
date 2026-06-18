package com.tripnest.core.services.impl;

import java.util.ArrayList;
import java.util.List;
import com.day.cq.commons.RangeIterator;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;

import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.tripnest.core.services.FindPageService;

@Component(service = FindPageService.class)
public class FingPageServiceImpl implements FindPageService {

    @Override
    public List<String> getFindPage(ResourceResolver resolver, String tagId) {

        List<String> pagePaths = new ArrayList<>();

        TagManager tagManager = resolver.adaptTo(TagManager.class);
        PageManager pageManager = resolver.adaptTo(PageManager.class);

        if (tagManager == null || pageManager == null) {
            return pagePaths;
        }

        RangeIterator<Resource> results = tagManager.find(tagId);

        while (results.hasNext()) {

            Resource resource = results.next();

            Page page = pageManager.getContainingPage(resource);

            if (page != null) {
                pagePaths.add(page.getPath());
            }
        }
        return pagePaths;
    }

}
