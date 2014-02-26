package com.waterfall.sample.configuration;

import java.util.Dictionary;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.log.LogService;

import com.google.common.base.Optional;

public class ConfigurationActivator implements Configurator {

    private LogService logger;
    private Dictionary configuration;
    
    protected void modified(ComponentContext context) {
        context.disableComponent("com.waterfall.sample.configuration");
        configuration = context.getProperties();
        logger = (LogService) context.locateService("LOG");
        logger.log(LogService.LOG_INFO, "configuration service modified.");
        context.enableComponent("com.waterfall.sample.configuration");
    }

    protected void activate(ComponentContext context) {
        logger = (LogService) context.locateService("LOG");
        configuration = context.getProperties();
        context.enableComponent("com.waterfall.sample.configuration");
    }

    /* reason for deactivation (int value): 
     * 0 – Unspecified.
     * 1 – The component was disabled.
     * 2 – A reference became unsatisfied.
     * 3 – A configuration was changed.
     * 4 – A configuration was deleted.
     * 5 – The component was disposed.
     * 6 – The bundle was stopped.
     */
    protected void deactivate(Integer reason) {
        /* TODO:jhq: i'm almost positive that there is an ENUM of these reasons somewhere */
        logger.log(LogService.LOG_INFO, "configuration service deactivated due to reason "+reason);
    }

    @Override
    public String getStringProperty(String name, final String defaultValue) {
        Optional<String> check = Optional.fromNullable((String) configuration.get(name));
        if (defaultValue==null) {
            return check.orNull();
        }
        return check.or(defaultValue);
    }
}
