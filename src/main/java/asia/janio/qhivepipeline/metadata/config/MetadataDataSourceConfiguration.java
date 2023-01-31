package asia.janio.qhivepipeline.metadata.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "metadataEntityManagerFactory",
        transactionManagerRef = "metadataTransactionManager",
        basePackages = {"asia.janio.qhivepipeline.metadata"}
)
public class MetadataDataSourceConfiguration {

    @Primary
    @Bean(name = "metadataDataSourceProperties")
    @ConfigurationProperties("spring.datasource.metadata")
    public DataSourceProperties metadataDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "metadataDataSource")
    @ConfigurationProperties("spring.datasource.metadata.config")
    public DataSource metadataDataSource(
            @Qualifier("metadataDataSourceProperties") DataSourceProperties metadataDataSourceProperties) {
        return metadataDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "metadataEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean metadataEntityManagerFactory(
            EntityManagerFactoryBuilder metadataEntityManagerFactoryBuilder,
            @Qualifier("metadataDataSource") DataSource metadataDataSource) {
        Map<String, String> metadataJpaProperties = new HashMap<>();
        metadataJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        metadataJpaProperties.put("hibernate.hbm2ddl.auto", "update");
        return metadataEntityManagerFactoryBuilder
                .dataSource(metadataDataSource)
                .packages("asia.janio.qhivepipeline.metadata")
                .persistenceUnit("metadataDataSource")
                .properties(metadataJpaProperties)
                .build();
    }

    @Primary
    @Bean(name = "metadataTransactionManager")
    public PlatformTransactionManager metadataTransactionManager(
            @Qualifier("metadataEntityManagerFactory") EntityManagerFactory metadataEntityManagerFactory) {
        return new JpaTransactionManager(metadataEntityManagerFactory);
    }
}
