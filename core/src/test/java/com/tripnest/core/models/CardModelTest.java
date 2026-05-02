package com.tripnest.core.models;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AemContextExtension.class)
class CardModelTest {

    private final AemContext context = new AemContext();
    private CardModel model;

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(CardModel.class);
    }

    @Test
    void testCardTitle() {
        Resource resource = context.create().resource("/content/test",
                "cardTitle", "My Title");

        model = resource.adaptTo(CardModel.class);

        assertEquals("My Title", model.getCardTitle());
    }

    @Test
    void testCardImage() {
        Resource resource = context.create().resource("/content/test",
                "cardImage", "/content/dam/image.png");

        model = resource.adaptTo(CardModel.class);

        assertEquals("/content/dam/image.png", model.getCardImage());
    }

    @Test
    void testCardDescription() {
        Resource resource = context.create().resource("/content/test",
                "cardDescription", "Sample description");

        model = resource.adaptTo(CardModel.class);

        assertEquals("Sample description", model.getCardDescription());
    }

    @Test
    void testCardLink_Internal() {
        Resource resource = context.create().resource("/content/test",
                "cardLink", "/content/page");

        model = resource.adaptTo(CardModel.class);

        assertEquals("/content/page.html", model.getCardLink());
    }

    @Test
    void testCardLink_External() {
        Resource resource = context.create().resource("/content/test",
                "cardLink", "https://google.com");

        model = resource.adaptTo(CardModel.class);

        assertEquals("https://google.com", model.getCardLink());
    }

    @Test
    void testCardLink_Null() {
        Resource resource = context.create().resource("/content/test");

        model = resource.adaptTo(CardModel.class);

        assertNull(model.getCardLink());
    }

    @Test
    void testOpenInNewTab_True() {
        Resource resource = context.create().resource("/content/test",
                "openInNewTab", true);

        model = resource.adaptTo(CardModel.class);

        assertTrue(model.getOpenInNewTab());
    }

    @Test
    void testOpenInNewTab_False() {
        Resource resource = context.create().resource("/content/test",
                "openInNewTab", false);

        model = resource.adaptTo(CardModel.class);

        assertFalse(model.getOpenInNewTab());
    }
    
}