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
import com.tripnest.core.services.PageSearchService;

@Component(service = PageSearchService.class, immediate = true)
public class PageSearchServiceImpl implements PageSearchService {

    private static final Logger LOG = LoggerFactory.getLogger(PageSearchServiceImpl.class);

    @Reference
    private QueryBuilder queryBuilder;

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public List<String> getPagePaths() {

        List<String> pagePaths = new ArrayList<>();

        Map<String, Object> param = new HashMap<>();
        param.put(resolverFactory.SUBSERVICE, "tripnest-subservice");
        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {

            Session session = resolver.adaptTo(Session.class);

            Map<String, String> predicates = new HashMap();
            predicates.put("path", "/content/tripnest");
            predicates.put("type", "cq:Page");
            predicates.put("p.limit", "-1");

            Query query = queryBuilder.createQuery(PredicateGroup.create(predicates), session);

            SearchResult result = query.getResult();

            for (Hit hit : result.getHits()) {
                pagePaths.add(hit.getPath());
            }

        } catch (LoginException | RepositoryException e) {
            LOG.info("Exception Found : {} ", e.getMessage());
        }
        return pagePaths;
    }

}
