//package asia.janio.qhivepipeline.hive.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.sql.Connection;
//
//@Configuration
//public class HiveJdbcConfiguration {
//
////    @Bean("hiveJdbcTemplate")
////    @Qualifier("hiveJdbcTemplate")
////    public JdbcTemplate jdbcTemplate(@Qualifier("hiveDruidDataSource") DataSource dataSource) {
////        return new JdbcTemplate(dataSource);
////    }
//
//    @Bean("hiveConnection")
//    @Qualifier("hiveConnection")
//    public Connection getConnection(HiveConnection connection) {
//        conn
//        return hiveConnection.getInstance().getConnection();
//    }
//
//}