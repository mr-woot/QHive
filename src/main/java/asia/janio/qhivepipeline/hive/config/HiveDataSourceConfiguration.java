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

    @Value("${hive.config.driverUrl}")
    private String driverUrl;

    public DataSource getHiveDataSource() throws IOException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(this.hiveConnectionURL);
        dataSource.setDriverClassName(driverUrl);
        dataSource.setUsername(this.userName);
        dataSource.setPassword(this.password);
        dataSource.setValidationQueryTimeout(5);
        dataSource.setTestOnBorrow(true);
        dataSource.setInitialSize(3);
        dataSource.setMaxWait(60000);
        return dataSource;
    }

    @Bean(name = "hiveTemplate")
    public JdbcTemplate getJDBCTemplate() throws IOException {
        return new JdbcTemplate(getHiveDataSource());
    }
}
