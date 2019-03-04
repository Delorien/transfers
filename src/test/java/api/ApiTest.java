package api;

import com.company.transfers.configuration.Persistence;
import com.intuit.karate.junit4.Karate;
import org.eclipse.jetty.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static com.company.transfers.configuration.ServletContextHelper.getServletContextHandler;

/**
 * Created by Leonardo Tonin on 04/03/19.
 */
@RunWith(Karate.class)
public class ApiTest {

    private static Server server;

    @BeforeClass
    public static void beforeClass() throws Exception {
        Persistence.getInstance().initializeDatabase();
        server = new Server(8081);
        server.setHandler(getServletContextHandler());
        server.start();
    }

    @AfterClass
    public static void stopJetty() throws Exception {
        server.stop();
    }
}
