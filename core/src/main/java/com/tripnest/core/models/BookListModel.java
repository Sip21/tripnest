package com.tripnest.core.models;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BookListModel {

    @ChildResource
    private List<BookItemModel> bookList;

    public List<BookItemModel> getBookList() {
        return bookList;
    }

    // @PostConstruct
    // protected void init() {
    // if (bookList != null) {
    // for (Resource item : bookList) {
    // String bookName = item.getValueMap().get("bookName", String.class);
    // String author = item.getValueMap().get("author", String.class);
    // String price = item.getValueMap().get("price", String.class);

    // books.add(new BookItem(bookName, author, price));
    // // output
    // // bookname1{bookname, author, price}
    // }
    // }
    // }

    // public List<BookItem> getBooks() {
    // return books;
    // }
}
