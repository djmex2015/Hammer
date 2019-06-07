package com.mex.SpringBootProject.configuration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.db.jdbc.ColumnConfig;
import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;
import org.apache.logging.log4j.core.filter.ThresholdFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import org.apache.logging.log4j.core.appender.db.ColumnMapping;

@Configuration
public class LogConfig {

    @Autowired
    private Environment env;

    @PostConstruct
    public void onStartUp() {
        String url = env.getProperty("spring.datasource.url");
        String userName = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");
        String validationQuery = env.getProperty("spring.datasource.validation-query");

        // Create a new connectionSource build from the Spring properties
        JdbcConnectionSource connectionSource = new JdbcConnectionSource(url, userName, password, validationQuery) {
        };

        // This is the mapping between the columns in the table and what to insert in it.
        ColumnConfig[] columnConfigs = new ColumnConfig[5];
        columnConfigs[0] = ColumnConfig.newBuilder().setName("APPLICATION").setPattern("ACCESS").setUnicode(false).build();
        columnConfigs[1] = ColumnConfig.newBuilder().setName("LOG_DATE").setEventTimestamp(true).build();
        columnConfigs[2] = ColumnConfig.newBuilder().setName("LOGGER").setPattern("%logger").setUnicode(false).build();
        columnConfigs[3] = ColumnConfig.newBuilder().setName("LOG_LEVEL").setPattern("%level").setUnicode(false).build();
        columnConfigs[4] = ColumnConfig.newBuilder().setName("MESSAGE").setPattern("%message").setUnicode(false).build();
        
        ColumnMapping[] columnMappings = new ColumnMapping[5];
        columnMappings[0] = ColumnMapping.newBuilder().setName("APPLICATION").setPattern("ACCESS").build();
        columnMappings[1] = ColumnMapping.newBuilder().setName("LOG_DATE").build();
        columnMappings[2] = ColumnMapping.newBuilder().setName("LOGGER").setPattern("%logger").build();
        columnMappings[3] = ColumnMapping.newBuilder().setName("LOG_LEVEL").setPattern("%level").build();
        columnMappings[4] = ColumnMapping.newBuilder().setName("MESSAGE").setPattern("%message").build();
        

        // filter for the appender to keep only errors
        ThresholdFilter filter = ThresholdFilter.createFilter(Level.ERROR, null, null);

        // The creation of the new database appender passing:
        // - the name of the appender
        // - ignore exceptions encountered when appending events are logged
        // - the filter created previously
        // - the connectionSource, 
        // - log buffer size, 
        // - the name of the table 
        // - the config of the columns.
        JdbcAppender appender = JdbcAppender.newBuilder().setColumnMappings(columnMappings).setName("DB").setIgnoreExceptions(true).setFilter(filter)
                .setConnectionSource(connectionSource).setBufferSize(1).setTableName("LOGS").setColumnConfigs(columnConfigs).build();

        // start the appender, and this is it...
        appender.start();
        ((Logger) LogManager.getRootLogger()).addAppender(appender);
    }
}
