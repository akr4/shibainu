package net.physalis.shibainu.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class JndiDatabaseConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(JndiDatabaseConfig.class);

    @Bean
    public DataSource dataSource() throws NamingException {
        String jndiName = "java:comp/env/jdbc/shibainuDs";
        LOGGER.info("getting datasource via JNDI with name {}", jndiName);
        return new TransactionAwareDataSourceProxy((DataSource) new InitialContext().lookup(jndiName));
    }
}
