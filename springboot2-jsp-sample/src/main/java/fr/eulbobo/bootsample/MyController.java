package fr.eulbobo.bootsample;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @GetMapping("/")
    public String home(Model model) {
        // redirection vers la page welcome.jsp
        model.addAttribute("time", LocalDate.now());
        return "welcome";
    }
    
    @GetMapping("/coucou")
    @ResponseBody
    public String coucou() {
        // affichage du message "coucou"
        return "coucou";
    }
}
