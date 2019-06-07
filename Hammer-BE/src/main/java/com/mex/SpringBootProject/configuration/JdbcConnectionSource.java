package com.mex.SpringBootProject.configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.logging.log4j.core.appender.db.jdbc.ConnectionSource;



public class JdbcConnectionSource implements ConnectionSource
{
  private final DataSource dataSource;

  public JdbcConnectionSource(String url, String userName, String password, String validationQuery)
  {
    Properties properties = new Properties();
    properties.setProperty("user", userName);
    properties.setProperty("password", password);

    GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<>();
    DriverManagerConnectionFactory cf = new DriverManagerConnectionFactory(url, properties);
    PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(cf, pool, null, validationQuery, 3, false, false, Connection.TRANSACTION_READ_COMMITTED);
    this.dataSource = new PoolingDataSource(pool);
  }

  @Override
  public Connection getConnection() throws SQLException
  {
    return dataSource.getConnection();
  }

    public State getState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void start() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isStarted() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isStopped() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
