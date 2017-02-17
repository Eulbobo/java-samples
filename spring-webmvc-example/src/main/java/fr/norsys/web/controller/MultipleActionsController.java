package fr.norsys.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/multipleActions")
public class MultipleActionsController {

    private final MessageSource messageSource;

    @Autowired
    public MultipleActionsController(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "multipleActions";
    }

    /**
     * On vérifier la présence du champ nommé "bouton_validation", quelle que soit sa valeur
     *
     * @param Locale pour la traduction
     * @return Redirection vers multipleActionsResult
     */
    @RequestMapping(method = RequestMethod.POST, params = "bouton_validation")
    public ModelAndView clicBoutonValidation(final Locale locale) {

        ModelAndView model = new ModelAndView("multipleActionsResult");
        model.addObject("content", messageSource.getMessage("controller.multipleactions.message",
                new Object[] { "bouton_validation" }, locale));

        return model;
    }

    /**
     * On vérifier la présence du champ nommé "bouton_annulation", quelle que soit sa valeur
     *
     * @param Locale pour la traduction
     * @param param : la valeur du paramètre du bouton
     * @return Redirection vers multipleActionsResult
     */
    @RequestMapping(method = RequestMethod.POST, params = "bouton_annulation")
    public ModelAndView clicBoutonAnnulation(final Locale locale,
            @RequestParam("bouton_annulation") final String param) {

        ModelAndView model = new ModelAndView("multipleActionsResult");
        model.addObject("content",
                messageSource.getMessage("controller.multipleactions.message", new Object[] { param }, locale));

        return model;
    }

    /**
     * On vérifier la présence du champ nommé "bouton", quelle que soit sa valeur
     *
     * @param Locale pour la traduction
     * @param param : la valeur du paramètre du bouton
     * @return Redirection vers multipleActionsResult
     */
    @RequestMapping(method = RequestMethod.POST, params = "bouton")
    public ModelAndView clicBoutonAction1(final Locale locale, @RequestParam("bouton") final String param) {

        ModelAndView model = new ModelAndView("multipleActionsResult");
        model.addObject("content",
                messageSource.getMessage("controller.multipleactions.message", new Object[] { param }, locale));

        return model;
    }

}
