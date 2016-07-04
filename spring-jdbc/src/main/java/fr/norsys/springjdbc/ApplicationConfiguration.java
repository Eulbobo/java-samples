package fr.norsys.springjdbc;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

/**
 * Dans cette configuration, nous allons utiliser une base de donn�es embarqu�e H2
 * Comme vous voyez, c'est TRES simple de configurer ce type de datasource
 *
 * Ce fonctionnement est id�al pour des tests d'int�gration complets
 */
@Configuration
@ComponentScan("fr.norsys.springjdbc")
public class ApplicationConfiguration {

    @Bean
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScripts("schema.sql", "data.sql")
                .build();
    }

}
