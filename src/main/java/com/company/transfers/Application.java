package com.company.transfers;

import com.company.transfers.configuration.JerseyConfiguration;
import com.company.transfers.configuration.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

/**
 * Created by Leonardo Tonin on 02/03/19.
 */
@Slf4j
public class Application {

    public static void main(String[] args) {
        setUpEmbeddedDatabase();
        startServer();
    }

    private static void setUpEmbeddedDatabase() {
        Persistence.getInstance().initializeDatabase();
    }

    private static void startServer() {
        Server server = new Server(8080);
        server.setHandler(getServletContextHandler());

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            System.exit(1);
        } finally {
            server.destroy();
        }
    }

    private static ServletContextHandler getServletContextHandler() {
        ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);
        servletContextHandler.setContextPath("/");
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(new JerseyConfiguration()));
        servletContextHandler.addServlet(servletHolder, "/api/*");
        return servletContextHandler;
    }
}
