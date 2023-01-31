package asia.janio.qhivepipeline.hive.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "hiveEntityManagerFactory",
        transactionManagerRef = "hiveTransactionManager",
        basePackages = {"asia.janio.qhivepipeline.hive"}
)
public class HiveDataSourceConfiguration {

    @Bean(name = "hiveDataSourceProperties")
    @ConfigurationProperties("spring.datasource.hive")
    public DataSourceProperties hiveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "hiveDataSource")
    public DataSource hiveDataSource(
            @Qualifier("hiveDataSourceProperties") DataSourceProperties hiveDataSourceProperties) {
        return hiveDataSourceProperties
                .initializeDataSourceBuilder()
                .build();
    }

}
