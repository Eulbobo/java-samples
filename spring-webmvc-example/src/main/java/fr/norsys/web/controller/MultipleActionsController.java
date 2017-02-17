package fr.norsys.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.norsys.web.support.MessageSupport;

@Controller
@RequestMapping(value = "/multipleActions")
public class MultipleActionsController {

    private final MessageSupport messageSupport;

    @Autowired
    public MultipleActionsController(final MessageSource messageSource) {
        this.messageSupport = new MessageSupport(messageSource);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "multipleActions";
    }

    // <input type="submit" name="validation" value="Validation" /><br/>
    // <input type="submit" name="annulation" value="Annulation" /><br/>
    // <input type="submit" name="bouton" value="Action1" /><br/>
    // <input type="submit" name="bouton" value="Action2" />

    /**
     * On vérifier la présence du champ nommé "validation", quelle que soit sa valeur
     *
     * @return Redirection vers multipleActionsResult
     */
    @RequestMapping(method = RequestMethod.POST, params = "bouton_validation")
    public ModelAndView clicBoutonValidation(final Locale locale) {

        ModelAndView model = new ModelAndView("multipleActionsResult");
        model.addObject("content",
                messageSupport.getMessage(locale, "controller.multipleactions.message", "bouton_validation"));

        return model;
    }

    /**
     * On vérifier la présence du champ nommé "annulation", quelle que soit sa valeur
     *
     * @param Locale
     * @param param : la valeur du paramètre du bouton (totalement overkill ici)
     * @return Redirection vers multipleActionsResult
     */
    @RequestMapping(method = RequestMethod.POST, params = "bouton_annulation")
    public ModelAndView clicBoutonAnnulation(final Locale locale,
            @RequestParam("bouton_annulation") final String param) {

        ModelAndView model = new ModelAndView("multipleActionsResult");
        model.addObject("content", messageSupport.getMessage(locale, "controller.multipleactions.message", param));

        return model;
    }

    /**
     * On vérifier la présence du champ nommé "bouton", quelle que soit sa valeur
     *
     * @param Locale
     * @param param : la valeur du paramètre du bouton
     * @return Redirection vers multipleActionsResult
     */
    @RequestMapping(method = RequestMethod.POST, params = "bouton")
    public ModelAndView clicBoutonAction1(final Locale locale, @RequestParam("bouton") final String param) {

        ModelAndView model = new ModelAndView("multipleActionsResult");
        model.addObject("content", messageSupport.getMessage(locale, "controller.multipleactions.message", param));

        return model;
    }

//    /**
//     * On vérifier la présence du champ nommé "bouton", quelle que soit sa valeur
//     *
//     * @param Locale
//     * @param param : la valeur du paramètre du bouton
//     * @return Redirection vers multipleActionsResult
//     */
//    @RequestMapping(method = RequestMethod.POST, params = "bouton={valeurBouton}")
//    public ModelAndView clicBoutonAction2(final Locale locale,
//            @RequestParam("valeurBouton") final String param) {
//
//        ModelAndView model = new ModelAndView("multipleActionsResult");
//        model.addObject("content", messageSupport.getMessage(locale, "controller.multipleactions.message", param));
//
//        return model;
//    }

}
