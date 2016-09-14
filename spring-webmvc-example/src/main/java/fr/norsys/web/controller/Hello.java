package fr.norsys.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class Hello {

    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView welcome() {
        ModelAndView model = new ModelAndView("HelloWorldPage");
        model.addObject("message", "Welcome here");

        return model;
    }

    @RequestMapping(path= "/helloWorld", method= RequestMethod.GET)
    public ModelAndView helloWorld() {
        ModelAndView model = new ModelAndView("HelloWorldPage");
        model.addObject("message", "hello world");

        return model;
    }
}
