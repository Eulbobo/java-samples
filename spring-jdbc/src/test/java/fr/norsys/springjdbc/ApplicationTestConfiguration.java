package fr.norsys.springjdbc;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
@ComponentScan("fr.norsys.springjdbc")
public class ApplicationTestConfiguration {

    /**
     * Création de la datasource de test
     * @return datasource pour les tests
     */
    @Bean
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("TESTDB")
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScripts("schema.sql", "data.sql")
                .build();
    }

    /**
     * Définition d'un transaction manager pour les tests
     */
    @Bean
    public DataSourceTransactionManager txManager(final DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }


}
