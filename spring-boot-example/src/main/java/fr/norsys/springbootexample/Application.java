package fr.norsys.springbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class Application {

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Ceci est une application Spring-boot!";
    }

    /**
     * Executez celle classe, et accédez au résultat via l'url
     * http://localhost:8090/
     *
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
