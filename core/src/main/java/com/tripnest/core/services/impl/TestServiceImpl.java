package com.tripnest.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.TestService;

@Component(service = TestService.class)
public class TestServiceImpl implements TestService {

    private static final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);
    // Step 1: Get the Resourceresolverfactory call inside the servceimpl
    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public String getPath(String path) {

        // Step 2: is to call the create resourceResolver from resolverFactory we need
        // to
        // create a param.
        Map<String, Object> param = new HashMap<>();
        param.put(resolverFactory.SUBSERVICE, "tripnest-subservice");

        // Step 3: We need to get now resourceResolver from resolverFactory
        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {

            // Step 4: We need to have resource from resolver now.
            Resource resource = resolver.getResource(path);

            // Step 5: Now that we have resource we need to check if it that resource exists
            // or not.
            if (resource != null) {
                return "Page Found " + "," + "Resource Name : " + resource.getName() + "," + " Has Children : "
                        + resource.hasChildren();
            }

        } catch (LoginException e) {
            log.error("LoginException while getting ResourceResolver for path: {}", path, e);
        }
        return "Page Not Found";
    }

}
