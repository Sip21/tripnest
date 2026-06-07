package com.tripnest.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.tripnest.core.services.BookService;

@Model(adaptables = { Resource.class,
        SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
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

    @OSGiService
    public BookService bookService;

    public String copyright;

    @PostConstruct
    protected void init() {
        // for service invocation
        if (bookService != null) {
            int year = bookService.getCurrentYear();
            copyright = "Copyright " + year;
        } else {
            copyright = "Copyright 2026";
        }

        // for @resourcepath
        if (contentRoot != null) {
            contentPath = contentRoot.getPath();

            // count children
            int count = 0;
            for (Resource child : contentRoot.getChildren()) {
                count++;
            }
            childrenCount = count;
        }

        // for request adaptable
        RequestPathInfo pathInfo = request.getRequestPathInfo();
        requestUri = request.getRequestURI();
        selectors = pathInfo.getSelectorString();
        extension = pathInfo.getExtension();

        // for Osgiservice to return formatted name
        if (bookService != null) {
            formattedName = bookService.getName(firstName, lastName);
        }
    }

    public String getCopyright() {
        return copyright;
    }

    @ResourcePath(path = "/content/tripnest")
    private Resource contentRoot;

    private String contentPath;
    private int childrenCount;

    public String getContentPath() {
        return contentPath;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    @Self
    private SlingHttpServletRequest request;

    private String requestUri;
    private String selectors;
    private String extension;

    public String getRequestUri() {
        return requestUri;
    }

    public String getSelectors() {
        return selectors;
    }

    public String getExtension() {
        return extension;
    }

    @ValueMapValue
    private String firstName;

    @ValueMapValue
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private String formattedName;

    public String getFormattedName() {
        return formattedName;
    }

}
