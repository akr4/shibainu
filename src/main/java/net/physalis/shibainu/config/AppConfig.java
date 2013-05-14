package net.physalis.shibainu.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@ComponentScan(
        basePackages = {
                "net.physalis.shibainu.config",
                "net.physalis.shibainu.domain",
                "net.physalis.shibainu.infrastructure",
        })
@EnableTransactionManagement
@PropertySource("classpath:/config/app-${app.env}.properties")
public class AppConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    private ConfigurableEnvironment env;

    @PostConstruct
    public void initProfiles() {
        String profiles = env.getRequiredProperty("profiles");
        LOGGER.info("profiles: " + profiles);
        env.setActiveProfiles(profiles);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public QueryDslJdbcTemplate queryDslJdbcTemplate(DataSource dataSource) {
        return new QueryDslJdbcTemplate(dataSource);
    }

}
