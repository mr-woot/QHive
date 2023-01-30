package asia.janio.qhivepipeline.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class HiveDataSourceConfiguration {

    @Bean(name = "hiveDataSourceProperties")
    @ConfigurationProperties("hive.datasource")
    public DataSourceProperties hiveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "hiveDataSource")
    public DataSource hiveDataSource() {
        return hiveDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

}
