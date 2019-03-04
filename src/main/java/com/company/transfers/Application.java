package com.company.transfers;

import com.company.transfers.configuration.Persistence;
import org.eclipse.jetty.server.Server;

import static com.company.transfers.configuration.ServletContextHelper.getServletContextHandler;

/**
 * Created by Leonardo Tonin on 02/03/19.
 */
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
}
