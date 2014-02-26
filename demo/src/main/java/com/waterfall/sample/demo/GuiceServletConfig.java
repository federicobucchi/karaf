package com.waterfall.sample.demo;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.osgi.service.component.ComponentContext;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.waterfall.sample.demo.resources.DemoResource;

public class GuiceServletConfig extends GuiceServletContextListener {

    private static final Logger logger = Logger.getLogger(GuiceServletConfig.class);

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                logger.info("Configuring servlets");
                bind(DemoResource.class);

                serve("/api/*").with(GuiceContainer.class);
            }
        });
    }

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        super.contextInitialized(sce);
        logger.info("contextInitialized");
        System.out.println("start context");
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        super.contextDestroyed(sce);
        logger.info("contextDestroyed");
        System.out.println("end context");
    }

    protected void activate(ComponentContext context) {
        logger.info("Activating {}", GuiceServletConfig.class.getName());
        System.out.println("start");

    }

    protected void deactivate(ComponentContext context) {
        logger.info("Deactivating {}", GuiceServletConfig.class.getName());
        System.out.println("stop");
    }

    protected void modified(ComponentContext context) {
        logger.info("Modified {}", GuiceServletConfig.class.getName());
        System.out.println("change");
    }
}
