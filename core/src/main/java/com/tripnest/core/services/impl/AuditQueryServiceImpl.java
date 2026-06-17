package com.tripnest.core.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.tripnest.core.models.AuditPOJO;
import com.tripnest.core.services.AuditQueryService;

@Component(service = AuditQueryService.class)
public class AuditQueryServiceImpl implements AuditQueryService {

    private static final Logger LOG = LoggerFactory.getLogger(AuditQueryServiceImpl.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Reference
    private QueryBuilder queryBuilder;

    @Override
    public List<AuditPOJO> getRecentlyModifiedPages() {
        List<AuditPOJO> pageDetails = new ArrayList<>();

        Map<String, Object> param = new HashMap<>();
        param.put(resolverFactory.SUBSERVICE, "tripnest-subservice");
        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {

            Session session = resolver.adaptTo(Session.class);

            Map<String, String> predicates = new HashMap<>();
            predicates.put("path", "/content/tripnest");
            predicates.put("type", "cq:Page");
            predicates.put("orderby", "@jcr:content/cq:lastModified");
            predicates.put("orderby", "@jcr:content/cq:lastModifiedBy");
            predicates.put("orderby.sort", "desc");
            predicates.put("p.limit", "10");

            Query query = queryBuilder.createQuery(PredicateGroup.create(predicates), session);

            SearchResult result = query.getResult();
            LOG.info("Hits Count:: {}", result.getHits().size());

            for (Hit hit : result.getHits()) {
                String path = hit.getPath();
                ValueMap properties = hit.getProperties();
                String modifiedDate = properties.get("cq:lastModified", String.class);
                String modifiedBy = properties.get("cq:lastModifiedBy", String.class);
                pageDetails.add(new AuditPOJO(path, modifiedDate, modifiedBy));
            }

        } catch (LoginException | RepositoryException e) {
            LOG.error("Fetch the exception {}", e);
        }
        return pageDetails;
    }
}
