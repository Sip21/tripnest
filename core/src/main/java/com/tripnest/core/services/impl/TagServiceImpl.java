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
import com.tripnest.core.services.TagService;

@Component(service = TagService.class)
public class TagServiceImpl implements TagService {

    private static final Logger LOG = LoggerFactory.getLogger(TagServiceImpl.class);

    @Reference
    private QueryBuilder queryBuilder;

    @Reference
    ResourceResolverFactory resolverFactory;

    @Override
    public List<String> getPagesWithTag() {

        List<String> pageWithTag = new ArrayList<>();

        Map<String, Object> param = new HashMap<>();
        param.put(resolverFactory.SUBSERVICE, "tripnest-subservice");

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {

            Session session = resolver.adaptTo(Session.class);

            Map<String, String> predicates = new HashMap<>();
            predicates.put("path", "/content/tripnest");
            predicates.put("type", "cq:Page");
            predicates.put("tagid", "tripnest:travel");
            predicates.put("tagid.property", "jcr:content/cq:tags");
            predicates.put("p.limit", "-1");

            Query query = queryBuilder.createQuery(PredicateGroup.create(predicates), session);
            LOG.info("Query :::: {}", query);

            SearchResult result = query.getResult();
            LOG.info("Hits Count : {}", result.getHits().size());

            for (Hit hit : result.getHits()) {
                pageWithTag.add(hit.getPath());
            }

        } catch (LoginException | RepositoryException e) {
            LOG.error("Exception here {}", e);
        }
        return pageWithTag;
    }
}
