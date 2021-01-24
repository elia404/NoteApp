package com.noteApp.be;
import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class ConnectionPool {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String JDBC_DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String JDBC_USER = "postgres";
    static final String JDBC_PASS = "noham";
    private static GenericObjectPool gPool = null;

    @SuppressWarnings("unused")
    public DataSource setUpPool() throws Exception {
        Class.forName(JDBC_DRIVER);
        gPool = new GenericObjectPool();
        gPool.setMaxActive(6);
        ConnectionFactory cf = new DriverManagerConnectionFactory(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
        PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
        return new PoolingDataSource(gPool);
    }

    public GenericObjectPool getConnectionPool() {
        return gPool;
    }


}
