package net.physalis.shibainu;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;
import net.physalis.shibainu.config.Env;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class LogbackInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackInitializer.class);

    public static void init() {
        URL url = getConfigUrl();
        if (url != null) {
            try {
                LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
                context.reset();
                new ContextInitializer(context).configureByResource(url);
            } catch (JoranException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static URL getConfigUrl() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL url = cl.getResource(getConfigFilename());
        if (url == null) {
            URL fallbackUrl = cl.getResource("logback.groovy");
            if (fallbackUrl == null) {
                LOGGER.warn("logback.groovy not found.");
                return null;
            } else {
                LOGGER.info("logback.groovy is used.");
                return fallbackUrl;
            }
        } else {
            return url;
        }
    }

    private static String getConfigFilename() {
        String env = Env.name();
        return "logback-" + env + ".groovy";
    }
}
