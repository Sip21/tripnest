package com.tripnest.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CardModel {

    @ValueMapValue
    private String cardTitle;
    @ValueMapValue
    private String cardImage;
    @ValueMapValue
    private String cardDescription;
    @ValueMapValue
    private String cardLink;
    @ValueMapValue
    private boolean openInNewTab;

    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardImage() {
        return cardImage;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public String getCardLink() {
        if (cardLink == null) {
            return null;
        }

        if (cardLink.startsWith("/content")) {
            return cardLink + ".html";
        }

        return cardLink;
    }

    public boolean getOpenInNewTab() {
        return openInNewTab;
    }

}
