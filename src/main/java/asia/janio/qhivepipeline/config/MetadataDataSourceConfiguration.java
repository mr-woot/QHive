package asia.janio.qhivepipeline.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@Configuration
public class MetadataDataSourceConfiguration {

    @Primary
    @Bean(name = "metadataDataSourceProperties")
    @ConfigurationProperties("spring.datasource.metadata")
    public DataSourceProperties metadataDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "metadataDataSource")
    public DataSource metadataDataSource() {
        return metadataDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        return sessionFactory;
    }
}
