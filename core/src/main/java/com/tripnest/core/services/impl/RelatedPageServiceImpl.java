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
import com.tripnest.core.services.RelatedPageService;

@Component(service = RelatedPageService.class)
public class RelatedPageServiceImpl implements RelatedPageService {

    @Override
    public List<String> getRelatedPages(ResourceResolver resolver, String currentPagePath, String tagId) {

        List<String> relatedPages = new ArrayList<>();

        TagManager tagManager = resolver.adaptTo(TagManager.class);
        PageManager pageManager = resolver.adaptTo(PageManager.class);
        if (tagManager == null || pageManager == null) {
            return relatedPages;
        }

        RangeIterator<Resource> results = tagManager.find(tagId);

        while (results.hasNext()) {
            Resource resource = results.next();
            Page page = pageManager.getContainingPage(resource);
            if (page != null && !page.getPath().equals(currentPagePath)) {
                relatedPages.add(page.getPath());
            }
        }
        return relatedPages;
    }

}
