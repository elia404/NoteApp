package com.noteApp.be;

import javax.sql.DataSource;
import org.apache.commons.dbcp.*;
import org.apache.commons.pool.impl.GenericObjectPool;
import java.sql.Connection;
import java.sql.SQLException;

public  class ConnectionPool {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String JDBC_DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String JDBC_USER = "postgres";
    static final String JDBC_PASS = "noham";
    private static final GenericObjectPool gPool =new GenericObjectPool(null);

    @SuppressWarnings("unused")
     public  static DataSource setUpPool() throws ClassNotFoundException {
            Class.forName(JDBC_DRIVER);
            gPool.setMaxActive(9);
            ConnectionFactory cf = new DriverManagerConnectionFactory(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
            PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool,null , null, false, true);

        return new PoolingDataSource(gPool);
}
        public  static Connection getConnectionPool() throws ClassNotFoundException, SQLException {
       DataSource dataSource= ConnectionPool.setUpPool();
            return (dataSource.getConnection());
        }

    }
