package com.tripnest.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EmployeeModel {

    @ValueMapValue
    private String name;

    @ValueMapValue
    private String designation;

    @ChildResource
    private List<SkillModel> skills;

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public List<SkillModel> getSkills() {
        return skills;
    }

}
