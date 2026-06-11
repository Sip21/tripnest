package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Toggle - Tripnest Configuration", description = "This is the Dicount Flag configuration for Tripnest website")
public @interface FeatureToggleConfig {

    @AttributeDefinition(name = "Enable Discount", description = "The flag to turn ON/OFF the Discount Flag")

    boolean discountFlag();

}
