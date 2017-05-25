package com.acar.saibei.jetty;

import org.apache.commons.lang3.CharEncoding;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Bootstrap {

    public static void main(String[] args) {
        boot("classpath:applicationContext.xml", 8080);
    }

    private static void boot(String contextPath, int port) {
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.setContextPath("/");
        servletContextHandler.setSessionHandler(new SessionHandler());

        XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
        applicationContext.setConfigLocations(contextPath);

        servletContextHandler.addServlet(
                new ServletHolder(new DispatcherServlet(applicationContext)),
                "/");

        servletContextHandler.addFilter(
                new FilterHolder(new CharacterEncodingFilter(CharEncoding.UTF_8, true)),
                "/*",
                EnumSet.allOf(DispatcherType.class));

        Server server = new Server(port);
        server.setHandler(servletContextHandler);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
