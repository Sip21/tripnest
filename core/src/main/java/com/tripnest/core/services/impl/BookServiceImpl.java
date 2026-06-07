package com.tripnest.core.services.impl;

import java.util.Calendar;

import org.osgi.service.component.annotations.Component;

import com.tripnest.core.services.BookService;

@Component(service = BookService.class)
public class BookServiceImpl implements BookService {

    @Override
    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    @Override
    public String getName(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return (firstName + " " + lastName).toUpperCase();
        } else {
            return "";
        }
    }
}
