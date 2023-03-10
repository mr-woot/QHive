//package asia.janio.qhivepipeline.hive.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//@Log4j2
//@Configuration
//@EnableConfigurationProperties({HiveJdbcProperties.class, DataSourceCommonProperties.class})
//public class HiveDruidConfiguration {
//
//    private final HiveJdbcProperties hiveJdbcProperties;
//
//    private final DataSourceCommonProperties dataSourceCommonProperties;
//
//    public HiveDruidConfiguration(HiveJdbcProperties hiveJdbcProperties, DataSourceCommonProperties dataSourceCommonProperties) {
//        this.hiveJdbcProperties = hiveJdbcProperties;
//        this.dataSourceCommonProperties = dataSourceCommonProperties;
//    }
//
//    @Bean("hiveDruidDataSource")
//    @Qualifier("hiveDruidDataSource")
//    public DataSource dataSource() {
//
//        DruidDataSource datasource = new DruidDataSource();
////        BasicDataSource datasource = new BasicDataSource();
//
//        // hive properties
//        datasource.setUrl(hiveJdbcProperties.getUrl());
//        datasource.setUsername(hiveJdbcProperties.getUsername());
//        datasource.setPassword(hiveJdbcProperties.getPassword());
//        datasource.setDriverClassName(hiveJdbcProperties.getDriverClassName());
//
//        // common properties
//        datasource.setInitialSize(dataSourceCommonProperties.getInitialSize());
//        datasource.setMinIdle(dataSourceCommonProperties.getMinIdle());
//        datasource.setMaxActive(dataSourceCommonProperties.getMaxActive());
//        datasource.setMaxWait(dataSourceCommonProperties.getMaxWait());
//        datasource.setTimeBetweenEvictionRunsMillis(dataSourceCommonProperties.getTimeBetweenEvictionRunsMillis());
//        datasource.setMinEvictableIdleTimeMillis(dataSourceCommonProperties.getMinEvictableIdleTimeMillis());
//        datasource.setValidationQuery(dataSourceCommonProperties.getValidationQuery());
//        datasource.setTestWhileIdle(dataSourceCommonProperties.isTestWhileIdle());
//        datasource.setTestOnBorrow(dataSourceCommonProperties.isTestOnBorrow());
//        datasource.setTestOnReturn(dataSourceCommonProperties.isTestOnReturn());
//        datasource.setPoolPreparedStatements(dataSourceCommonProperties.isPoolPreparedStatements());
//        try {
//            datasource.setFilters(dataSourceCommonProperties.getFilters());
//        } catch (SQLException e) {
//            log.error("Druid configuration initialization filter error.", e);
//        }
//        return datasource;
//    }
//}