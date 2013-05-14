package net.physalis.shibainu.presentation.web;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configurable
@ComponentScan
public class WebConfig extends WebMvcConfigurationSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public ViewResolver handlerbarsViewResolver() {
        HandlebarsViewResolver r = new HandlebarsViewResolver();
        r.setPrefix("/WEB-INF/web/view");
        r.setSuffix(".hbs");
        r.setContentType("text/html; charset=utf-8");
        r.setOrder(1);
        r.setCache(env.getProperty("handlebars.cache", Boolean.class, true));
        return r;
    }

    @Override
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping m = super.requestMappingHandlerMapping();
        m.setRemoveSemicolonContent(false);
        m.setUseTrailingSlashMatch(false);
        return m;
    }

}
