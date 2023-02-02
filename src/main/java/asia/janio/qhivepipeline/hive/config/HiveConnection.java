package asia.janio.qhivepipeline.hive.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class HiveConnection {

    private static final Logger logger = LoggerFactory.getLogger(HiveConnection.class);

    private static final String HIVE_JDBC_URI = "jdbc:hive2://ip-172-31-0-55.ap-southeast-1.compute.internal:2181," +
            "ip-172-31-10-50.ap-southeast-1.compute.internal:2181," +
            "ip-172-31-10-79.ap-southeast-1.compute.internal:2181/;" +
            "serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2";

    private static final String HIVE_USER = "hive";

    private static final String HIVE_PASSWORD = "";

    private static volatile HiveConnection instance;

    private static final Object mutex = new Object();

    private static Connection connection = null;

    private HiveConnection() {
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            Properties properties = new Properties();
            DataSourceCommonProperties commonProperties = new DataSourceCommonProperties();
            properties.setProperty("initialSize", String.valueOf(commonProperties.getInitialSize()));
            properties.setProperty("minIdle", String.valueOf(commonProperties.getMinIdle()));
            properties.setProperty("maxIdle", String.valueOf(commonProperties.getMaxIdle()));
            properties.setProperty("maxActive", String.valueOf(commonProperties.getMaxActive()));
            properties.setProperty("maxWait", String.valueOf(commonProperties.getMaxWait()));
            properties.setProperty("timeBetweenEvictionRunsMillis", String.valueOf(commonProperties.getTimeBetweenEvictionRunsMillis()));
            properties.setProperty("minEvictableIdleTimeMillis", String.valueOf(commonProperties.getMinEvictableIdleTimeMillis()));
            properties.setProperty("validationQuery", commonProperties.getValidationQuery());
            properties.setProperty("testWhileIdle", String.valueOf(commonProperties.isTestWhileIdle()));
            properties.setProperty("testOnBorrow", String.valueOf(commonProperties.isTestOnBorrow()));
            properties.setProperty("testOnReturn", String.valueOf(commonProperties.isTestOnReturn()));
            properties.setProperty("poolPreparedStatements", String.valueOf(commonProperties.isPoolPreparedStatements()));
            properties.setProperty("maxOpenPreparedStatements", String.valueOf(commonProperties.getMaxOpenPreparedStatements()));
            properties.setProperty("filters", commonProperties.getFilters());
            connection = DriverManager.getConnection(HIVE_JDBC_URI, properties);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Connection ERROR: ", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static HiveConnection getInstance() {
        HiveConnection result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    instance = result = new HiveConnection();
                }
            }
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new HiveConnection();
                }
            } catch (SQLException e) {
                logger.error("Connection Closed ERROR: ", e);
            }
        }
        return result;
    }
}
