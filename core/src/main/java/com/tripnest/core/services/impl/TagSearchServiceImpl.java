package com.tripnest.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;

import com.day.cq.commons.RangeIterator;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.tripnest.core.services.TagSearchService;

@Component(service = TagSearchService.class)
public class TagSearchServiceImpl implements TagSearchService {

    @Override
    public List<String> getPages(ResourceResolver resolver, String tagId) {

        List<String> pagePath = new ArrayList<>();

        TagManager tagManager = resolver.adaptTo(TagManager.class);
        PageManager pageManager = resolver.adaptTo(PageManager.class);

        if (tagManager == null || pageManager == null) {
            return pagePath;
        }

        RangeIterator<Resource> results = tagManager.find(tagId);
        while (results.hasNext()) {
            Resource resource = results.next();
            Page page = pageManager.getContainingPage(resource);
            if (page != null) {
                pagePath.add(page.getPath());
            }
        }
        return pagePath;
    }

}
