package asia.janio.qhivepipeline.hive.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            connection = DriverManager.getConnection(HIVE_JDBC_URI, HIVE_USER, HIVE_PASSWORD);
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
