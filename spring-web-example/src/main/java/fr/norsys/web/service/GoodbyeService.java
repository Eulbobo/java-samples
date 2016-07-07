package fr.norsys.web.service;

import org.springframework.stereotype.Service;

/**
 * Simple service pour prouver que l'injection fonctionne
 */
@Service
public class GoodbyeService implements ISpeakService {

    @Override
    public String speak() {
        return "Good bye !";
    }
}
