package fr.norsys.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.norsys.web.bean.Greeting;

@Controller
@RequestMapping(value = "/greeting")
public class GreetingController {

    /**
     * Methode get : arrivée initiale sur la page
     *
     * @param model model
     * @return lien vers la page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String greetingForm(final Model model) {
        model.addAttribute("greetingForm", new Greeting());
        return "greeting";
    }

    /**
     * Soumission de formulaire par défaut
     *
     * @param greetingForm formulaire du modèle
     * @param result resultat de validation
     * @return lien vers la page
     */
    @RequestMapping(method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute("greetingForm") @Valid final Greeting greetingForm,
            final BindingResult result) {
        if (result.hasErrors()) {
            return "greeting";
        }

        return "result";
    }

    /**
     * Soumission de formulaire avec id égal à 12 : pas de validation du bean
     *
     * @param greetingForm formulaire du modèle
     * @return lien vers la page
     */
    @RequestMapping(method = RequestMethod.POST, params = "id=12")
    public String greetingWithoutValidation(@ModelAttribute("greetingForm") final Greeting greetingForm) {
        greetingForm.setContent("We didn't validate anything");
        return "result";
    }

    /**
     * Soumission de formulaire avec contenu égal à "bob", mais id != 12
     * si on enlève le id!=12 des params et qu'on poste le formulaire avec bob et 12, on aura un plantage
     *
     * @param greetingForm formulaire du modèle
     * @param result resultat de validation
     * @return lien vers la page
     */
    @RequestMapping(method = RequestMethod.POST, params = "content=bob,id!=12")
    public String greetingSubmitForBob(@ModelAttribute("greetingForm") @Valid final Greeting greetingForm,
            final BindingResult result) {
        if (result.hasErrors()) {
            return "greeting";
        }
        greetingForm.setContent("Hello bob");

        return "result";
    }

}
