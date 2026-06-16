package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Tripnest - Country Configuration", description = "Country Configuration for Tripnest Website")
public @interface CountryConfig {

    @AttributeDefinition(name = "Site Name", description = "Please enter greeting message for your site")

    String siteName();

    @AttributeDefinition(name = "Country Name", description = "Please enter the country name")

    String countryName();

}
