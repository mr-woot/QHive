package asia.janio.qhivepipeline.hive.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HiveJdbcConfiguration {

//    @Bean("hiveJdbcTemplate")
//    @Qualifier("hiveJdbcTemplate")
//    public JdbcTemplate jdbcTemplate(@Qualifier("hiveDruidDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }

    @Bean("hiveConnection")
    @Qualifier("hiveConnection")
    public org.apache.hive.jdbc.HiveConnection getTemplate() {
        return (org.apache.hive.jdbc.HiveConnection) HiveConnection.getInstance().getConnection();
    }

}