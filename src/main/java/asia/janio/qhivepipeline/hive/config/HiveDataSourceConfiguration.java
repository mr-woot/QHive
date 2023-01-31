package asia.janio.qhivepipeline.hive.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class HiveDataSourceConfiguration {
    @Value("${hive.config.url}")
    private String hiveConnectionURL;

    @Value("${hive.config.username}")
    private String userName;

    @Value("${hive.config.password}")
    private String password;

    @Value("${hive.config.driver-class-name")
    private String driverClassName;

    public DataSource getHiveDataSource() throws IOException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(this.hiveConnectionURL);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(this.userName);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "hiveTemplate")
    public JdbcTemplate getJDBCTemplate() throws IOException {
        return new JdbcTemplate(getHiveDataSource());
    }
}
