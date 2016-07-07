package fr.norsys.web.service;

import org.springframework.stereotype.Service;

/**
 * Simple service pour prouver que l'injection fonctionne
 */
@Service
public class StillAliveService implements ISpeakService {

    @Override
    public String speak() {
        return "I'm still alive";
    }
}
