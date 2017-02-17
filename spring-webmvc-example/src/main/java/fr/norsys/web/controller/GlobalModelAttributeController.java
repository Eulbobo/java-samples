package fr.norsys.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.norsys.web.aop.annotation.LogAround;
import fr.norsys.web.bean.Thing;

@Controller
@RequestMapping("/thingsAndStuff")
public class GlobalModelAttributeController {

    /**
     * Cette méthode sera appelée à chaque requête et remplira automatiquement une liste appelée things
     *
     * @return listOfThings
     */
    @LogAround
    @ModelAttribute("things")
    public List<String> listOfThings() {
        List<String> listOfThings = new ArrayList<>();
        listOfThings.add("bidule");
        listOfThings.add("machin");
        listOfThings.add("truc");
        return listOfThings;
    }

    /**
     * Point d'entrée du controleur, on initialise le bean avec une valeur par défaut (non présente dans la liste)
     *
     * @return ModelAndView de base
     */
    @LogAround
    @RequestMapping
    public ModelAndView home() {
        return new ModelAndView("helloThings", "thingParam", new Thing("welcome"));
    }

    /**
     * Méthode quand on poste le formulaire -> on retourne à la page d'entrée avec la valeur sélectionnée dans la liste
     * déroulante
     *
     * @param thing modèle du formulaire
     * @return ModelAndView de base
     */
    @LogAround
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView postIt(@ModelAttribute("thingParam") final Thing thing) {
        return new ModelAndView("helloThings", "thingParam", thing);
    }
}
