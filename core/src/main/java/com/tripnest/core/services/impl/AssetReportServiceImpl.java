package com.tripnest.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;

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
import com.tripnest.core.services.AssetReportService;

@Component(service = AssetReportService.class)
public class AssetReportServiceImpl implements AssetReportService {

    private static final Logger LOG = LoggerFactory.getLogger(AssetReportServiceImpl.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Reference
    private QueryBuilder queryBuilder;

    private int assetCount;

    @Override
    public void generateReport() {
        Map<String, Object> param = new HashMap<>();
        param.put(resolverFactory.SUBSERVICE, "tripnest-subservice");
        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {

            Session session = resolver.adaptTo(Session.class);
            Map<String, String> predicates = new HashMap<>();
            predicates.put("path", "/content/dam/tripnest");
            predicates.put("type", "dam:Asset");
            predicates.put("p.limit", "-1");
            Query query = queryBuilder.createQuery(PredicateGroup.create(predicates), session);

            SearchResult result = query.getResult();

            int count = 0;
            for (Hit hit : result.getHits()) {
                count++;
            }
            assetCount = count;

        } catch (Exception e) {
            LOG.error("Exception found {}", e);
        }
    }

    @Override
    public int getAssetCount() {
        return assetCount;
    }

}
