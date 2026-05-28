package com.tripnest.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.tripnest.core.models.ExploreSectionsModel.Panels;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ExploreSectionsModel {

    private List<Panels> panels = new ArrayList<>();

    @Self
    private Resource resource;

    @PostConstruct
    protected void init() {
        Resource panelsResource = resource.getChild("panels");

        if (panelsResource != null) {
            for (Resource item : panelsResource.getChildren()) {
                Panels panel = new Panels(item.getValueMap().get("paneltitle", String.class),
                        item.getValueMap().get("panelbody", String.class),
                        item.getValueMap().get("defaultopen", Boolean.class));
                if (panel != null) {
                    panels.add(panel);
                }
            }
        }
    }

    public List<Panels> getPanels() {
        return panels;
    }

    public static class Panels {
        private final String panelTitle;
        private final String panelBody;
        private final Boolean defaultOpen;

        public Panels(String panelTitle, String panelBody, Boolean defaultOpen) {
            this.panelTitle = panelTitle;
            this.panelBody = panelBody;
            this.defaultOpen = defaultOpen;
        }

        public String getPanelTitle() {
            return panelTitle;
        }

        public String getPanelBody() {
            return panelBody;
        }

        public Boolean getDefaultOpen() {
            return defaultOpen;
        }
    }

}
