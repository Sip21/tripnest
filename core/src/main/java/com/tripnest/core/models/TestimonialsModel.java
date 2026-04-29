package com.tripnest.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TestimonialsModel {

    private List<TestimonialsItem> testimonialItems = new ArrayList<>();

    @Self
    private Resource resource;

    @PostConstruct
    protected void init() {
        Resource TestimonialsResource = resource.getChild("testimonialItems");

        if (TestimonialsResource != null) {
            for (Resource item : TestimonialsResource.getChildren()) {

                TestimonialsItem testimonialsItem = new TestimonialsItem(
                        item.getValueMap().get("name", String.class),
                        item.getValueMap().get("review", String.class),
                        item.getValueMap().get("image", String.class));

                testimonialItems.add(testimonialsItem);
            }
        }
    }

    public List<TestimonialsItem> getTestimonialItems() {
        return testimonialItems;
    }

    public static class TestimonialsItem {
        private final String name;
        private final String review;
        private final String image;

        public TestimonialsItem(String name, String review, String image) {
            this.name = name;
            this.review = review;
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public String getReview() {
            return review;
        }

        public String getImage() {
            return image;
        }
    }
}
