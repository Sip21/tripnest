package com.tripnest.core.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.tripnest.core.models.ContentElementResponse;
import com.tripnest.core.models.ContentFragmentResponse;
import com.tripnest.core.services.ContentFragmentService;

@Component(service = ContentFragmentService.class)
public class ContentFragmentServiceImpl implements ContentFragmentService {
    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    private static final Logger LOG = LoggerFactory.getLogger(ContentFragmentServiceImpl.class);

    @Override
    public Map<String, String> readFragment(String fragmentPath) {

        Map<String, Object> authInfo = new HashMap<>();
        authInfo.put(ResourceResolverFactory.SUBSERVICE, "tripnest-subservice");

        try (ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(authInfo)) {

            Resource resource = resolver.getResource(fragmentPath);

            if (resource == null) {
                return Map.of("error", "Fragment not found");
            }

            ContentFragment fragment = resource.adaptTo(ContentFragment.class);

            if (fragment == null) {
                return Map.of("error", "Fragment not found");
            }

            Map<String, String> result = new HashMap<>();
            result.put("title", fragment.getElement("title").getContent());
            result.put("description", fragment.getElement("description").getContent());

            return result;

        } catch (LoginException e) {
            LOG.error("Unable to read Content Fragment", e);
        }

        return Map.of("error", "Unable to read fragment");
    }

    @Override
    public List<ContentFragmentResponse> getAllFragments(String folderPath) {

        List<ContentFragmentResponse> responseList = new ArrayList<>();

        Map<String, Object> authInfo = new HashMap<>();
        authInfo.put(ResourceResolverFactory.SUBSERVICE, "tripnest-subservice");
        try (ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(authInfo)) {
            Resource folder = resolver.getResource(folderPath);
            if (folder == null) {
                return responseList;
            }
            for (Resource child : folder.getChildren()) {

                ContentFragment fragment = child.adaptTo(ContentFragment.class);
                if (fragment == null) {
                    continue;
                }

                List<ContentElementResponse> elements = new ArrayList<>();

                Iterator<ContentElement> iterator = fragment.getElements();
                while (iterator.hasNext()) {
                    ContentElement element = iterator.next();
                    elements.add(new ContentElementResponse(element.getName(), element.getContent()));
                }
                responseList.add(new ContentFragmentResponse(fragment.getName(), elements));
            }
        } catch (LoginException e) {
            LOG.error("Error reading fragments", e);
        }
        return responseList;
    }
}
