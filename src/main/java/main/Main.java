package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

/**
 * Created by Admin on 23.02.2016.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        AccountService accountService = new AccountService();

//        accountService.addNewUser(new UserProfile("admin"));
//        accountService.addNewUser(new UserProfile("test"));

//        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
//        MirrorRequestsServlet mirrorServlet = new MirrorRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.addServlet(new ServletHolder(allRequestsServlet), "/*");
//        context.addServlet(new ServletHolder(mirrorServlet), "/mirror");
//        context.addServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
//        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        //server.setHandler(context);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();



    }

}
