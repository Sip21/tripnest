package com.tripnest.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.tripnest.core.services.DiscountService;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DiscountModel {

    @ValueMapValue
    private int price;

    @ValueMapValue
    private int discount;

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    @OSGiService
    public DiscountService discountservice;

    private int output;

    @PostConstruct
    protected void init() {
        if (discountservice != null) {
            output = discountservice.getOutput(price, discount);
        }
    }

    public int getOutput() {
        return output;
    }

}
