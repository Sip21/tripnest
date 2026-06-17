package com.tripnest.core.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.tripnest.core.services.PageTitleService;

@Component(service = PageTitleService.class, immediate = true)
public class PageTitleServiceImpl implements PageTitleService {

    private static final Logger LOG = LoggerFactory.getLogger(PageTitleServiceImpl.class);

    @Reference
    ResourceResolverFactory resolverFactory;

    @Reference
    private QueryBuilder queryBuilder;

    @Override
    public List<String> getPageByTitle() {
        // I need a list , where i can add each path and return to service
        List<String> pagePaths = new ArrayList<>();

        // Now I need to adapt to session first and for that i need resolver and i cna
        // get that from resourceresolervfactory only.
        Map<String, Object> param = new HashMap<>();
        param.put(resolverFactory.SUBSERVICE, "tripnest-subservice");

        // Getting resolver from resolevrfactory
        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {

            // Adapt to session now
            Session session = resolver.adaptTo(Session.class);

            // Need to cerate Map now
            Map<String, String> predicates = new HashMap<>();
            predicates.put("path", "/content/tripnest");
            predicates.put("type", "cq:Page");
            predicates.put("property", "jcr:content/jcr:title");
            predicates.put("property.value", "%Come to%");
            predicates.put("property.operation", "like");
            predicates.put("p.limit", "-1");

            // Now need to create the Query with these predicates
            Query query = queryBuilder.createQuery(PredicateGroup.create(predicates), session);

            // now need to get the results from this query
            SearchResult result = query.getResult();

            // Now need to get hit from this result in for loop and return every page path
            for (Hit hit : result.getHits()) {
                pagePaths.add(hit.getPath());
            }

        } catch (RepositoryException | LoginException e) {
            LOG.error("Error while fetching pages {}", e);
        }
        return pagePaths;
    }

}
