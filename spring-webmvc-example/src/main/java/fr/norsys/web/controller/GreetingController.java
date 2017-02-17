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
public class GreetingController {

    @RequestMapping(value="/greeting", method=RequestMethod.GET)
    public String greetingForm(final Model model) {
        model.addAttribute("greetingForm", new Greeting());
        return "greeting";
    }

    @RequestMapping(value="/greeting", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute("greetingForm") @Valid final Greeting greetingForm, final BindingResult result) {
        if (result.hasErrors()){
            return "greeting";
        }

        return "result";
    }

    @RequestMapping(value="/greeting", method=RequestMethod.POST, params="content=bob")
    public String greetingSubmitForBob(@ModelAttribute("greetingForm") @Valid final Greeting greetingForm, final BindingResult result) {
        if (result.hasErrors()){
            return "greeting";
        }
        greetingForm.setContent("Hello bob");

        return "result";
    }

    @RequestMapping(value="/greeting", method=RequestMethod.POST, params="id=12")
    public String greetingWithoutValidation(@ModelAttribute("greetingForm") final Greeting greetingForm) {
        greetingForm.setContent("We didn't validate anything");

        return "result";
    }
}
