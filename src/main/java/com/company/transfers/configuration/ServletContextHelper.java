package com.company.transfers.configuration;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

/**
 * Created by Leonardo Tonin on 04/03/19.
 */
public class ServletContextHelper {

    public static ServletContextHandler getServletContextHandler() {
        ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);
        servletContextHandler.setContextPath("/");
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(new JerseyConfiguration()));
        servletContextHandler.addServlet(servletHolder, "/api/*");
        return servletContextHandler;
    }
}
