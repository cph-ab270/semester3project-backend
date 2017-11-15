package org.cba.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class DeploymentConfiguration implements ServletContextListener {

    public static String PU_NAME = "CA3";

    @Override
    @SuppressWarnings("empty-statement")
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("######################################################################################");
        System.out.println("############################ Deployment configuration fired! ####################################");
        System.out.println("######################################################################################");

        loadConfigProperties();
    }

    private void loadConfigProperties() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("/config.properties");
            if (input == null) {
                System.out.println("Could not load init-properties");
                return;
            }
            Properties prop = new Properties();
            prop.load(input);
            Config.SECRET_SIGNATURE = prop.getProperty("tokenSecret").getBytes();
            input.close();
        } catch (IOException e) {
            Logger.getLogger(DeploymentConfiguration.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

