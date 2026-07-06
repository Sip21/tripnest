package com.tripnest.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;

import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.Group;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/authorcheck",
        "sling.servlet.methods=GET" })
public class UserGroupCheckServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    private static final String TARGET_GROUP = "content-authors";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        ResourceResolver resolver = request.getResourceResolver();
        UserManager userManager = resolver.adaptTo(UserManager.class);
        if (userManager == null) {
            response.getWriter().write("Not Author");
            return;
        }
        try {
            Authorizable currentUser = userManager.getAuthorizable(resolver.getUserID());
            if (currentUser == null || currentUser.isGroup()) {
                response.getWriter().write("Not Author");
                return;
            }
            boolean isAuthor = isMemberOfGroup(currentUser, TARGET_GROUP);
            response.getWriter().write(isAuthor ? "Author" : "Not Author");

        } catch (RepositoryException e) {
            response.getWriter().write("Not Author");
        }

    }

    private boolean isMemberOfGroup(Authorizable currentUser, String groupId) throws RepositoryException {
        Iterator<Group> groups = currentUser.memberOf();
        while (groups.hasNext()) {
            Group group = groups.next();
            if (groupId.equals(group.getID())) {
                return true;
            }
        }
        return false;
    }

}
