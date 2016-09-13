package fr.norsys.springbootexample;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    /**
     * Executez celle classe, et accédez au résultat via l'url
     * http://localhost:8090/
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
