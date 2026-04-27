package com.tripnest.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DestinationCardsModel {

    private List<Card> cards = new ArrayList<>();

    @Self
    private Resource resource;

    @PostConstruct
    protected void init() {

        Resource cardsResource = resource.getChild("cards");

        if (cardsResource != null) {
            for (Resource item : cardsResource.getChildren()) {

                Card card = new Card(
                        item.getValueMap().get("title", String.class),
                        item.getValueMap().get("image", String.class),
                        item.getValueMap().get("description", String.class),
                        item.getValueMap().get("link", String.class));

                cards.add(card);
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    // CHANGE: Added inner Card class to resolve type error
    public static class Card {
        private final String title;
        private final String image;
        private final String description;
        private final String link;

        public Card(String title, String image, String description, String link) {
            this.title = title;
            this.image = image;
            this.description = description;
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getDescription() {
            return description;
        }

        public String getLink() {
            if (link != null && link.startsWith("/content")) {
                return link + ".html";
            }
            return link;
        }
    }
}