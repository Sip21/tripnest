package com.tripnest.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ExporterConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
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

    @JsonProperty("cardTitle")
    public String getCardTitle() {
        return cardTitle;
    }

    @JsonProperty("cardImage")
    public String getCardImage() {
        return cardImage;
    }

    @JsonProperty("cardDescription")
    public String getCardDescription() {
        return cardDescription;
    }

    @JsonProperty("cardLink")
    public String getCardLink() {

        if (cardLink == null) {
            return null;
        }

        if (cardLink.startsWith("/content")) {
            return cardLink + ".html";
        }

        return cardLink;
    }

    @JsonProperty("openInNewTab")
    public boolean getOpenInNewTab() {
        return openInNewTab;
    }

    // Example internal method excluded from JSON
    @JsonIgnore
    public String getInternalData() {
        return "hidden";
    }

}
