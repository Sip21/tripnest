package com.tripnest.core.models;

import java.util.ArrayList;
import java.util.List;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BookModel {

    @ChildResource
    private List<Resource> books;

    public List<String> getBooks() {
        List<String> title = new ArrayList<>();
        if (books != null) {
            for (Resource book : books) {
                String titleName = book.getValueMap().get("title", String.class);
                title.add(titleName);
            }
        }
        return title;
    }

}
