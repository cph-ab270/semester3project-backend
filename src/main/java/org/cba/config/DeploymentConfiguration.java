package org.cba.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DeploymentConfiguration implements ServletContextListener {

    public static String PU_NAME = "CA3";

    @Override
    @SuppressWarnings("empty-statement")
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("######################################################################################");
        System.out.println("############################ Deployment configuration fired! ####################################");
        System.out.println("######################################################################################");

        loadEnvironmentalVariables();
    }

    private void loadEnvironmentalVariables() {
        Env.SECRET_TOKEN = System.getenv("PROP_SECRET_TOKEN").getBytes();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

