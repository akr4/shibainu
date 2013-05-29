package net.physalis.shibainu;

import net.physalis.shibainu.config.AppConfig;
import net.physalis.shibainu.config.Env;
import net.physalis.shibainu.presentation.admin.AdminConfig;
import net.physalis.shibainu.presentation.api.ApiConfig;
import net.physalis.shibainu.presentation.web.WebConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.EnumSet;
import java.util.Properties;

public class MyWebAppInitializer implements WebApplicationInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyWebAppInitializer.class);

    @Override
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);
        container.addListener(new ContextLoaderListener(rootContext));

        String activeProfiles = findActiveProfiles();
        LOGGER.info("active profiles: " + activeProfiles);
        container.setInitParameter(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, activeProfiles);

        LogbackInitializer.init();

        web(container);
        admin(container);
        api(container);
    }

    private String findActiveProfiles() {
        String envName = Env.name();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String configFileName = String.format("/config/app-%s.properties", envName);
        URL configUrl = classLoader.getResource(configFileName);
        if (configUrl == null) {
            throw new IllegalStateException(String.format("config file (%s) is not found.", configFileName));
        }
        try (InputStream in = configUrl.openStream()) {
            Properties props = new Properties();
            props.load(in);

            return props.getProperty("profiles");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void web(ServletContext container) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);

        ServletRegistration.Dynamic servlet =
                container.addServlet("webServlet", new DispatcherServlet(context));
        servlet.addMapping("/app/*");

        FilterRegistration.Dynamic mdcFilter = container.addFilter("mdcFilter", new MdcFilter());
        mdcFilter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), false, "webServlet");
    }

    private void api(ServletContext container) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(ApiConfig.class);

        ServletRegistration.Dynamic servlet =
                container.addServlet("apiServlet", new DispatcherServlet(context));
        servlet.addMapping("/api/*");
    }

    private void admin(ServletContext container) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AdminConfig.class);

        ServletRegistration.Dynamic servlet =
                container.addServlet("adminServlet", new DispatcherServlet(context));
        servlet.addMapping("/admin/*");
    }
}
