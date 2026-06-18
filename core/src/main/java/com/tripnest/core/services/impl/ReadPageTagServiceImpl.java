package com.tripnest.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.tripnest.core.services.ReadPageTagService;

@Component(service = ReadPageTagService.class)
public class ReadPageTagServiceImpl implements ReadPageTagService {

    @Override
    public List<String> getPageTag(ResourceResolver resolver, String pagePath) {

        List<String> tagTitles = new ArrayList<>();

        PageManager pageManager = resolver.adaptTo(PageManager.class);
        Page page = pageManager.getPage(pagePath);
        ValueMap vm = page.getProperties();
        String[] tags = vm.get("cq:tags", String[].class);

        if (tags == null) {
            return tagTitles;
        }

        TagManager tagManager = resolver.adaptTo(TagManager.class);
        if (tagManager == null) {
            return tagTitles;
        }
        for (String tagId : tags) {
            Tag tag = tagManager.resolve(tagId);

            if (tag != null) {
                tagTitles.add(tag.getTitle());
            }
        }
        return tagTitles;
    }

}
