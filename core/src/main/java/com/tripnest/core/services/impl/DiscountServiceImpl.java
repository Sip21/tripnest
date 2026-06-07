package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Component;

import com.tripnest.core.services.DiscountService;

@Component(service = DiscountService.class)
public class DiscountServiceImpl implements DiscountService {

    @Override
    public Integer getOutput(int price, int discount) {
        if (price != 0 && discount != 0) {
            return price - ((discount * price) / 100);
        } else {
            return 0;
        }
    }

}
