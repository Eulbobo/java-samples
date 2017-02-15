package fr.norsys.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class Hello {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome() {
        ModelAndView model = new ModelAndView("HelloWorldPage");
        model.addObject("message", "Welcome here");

        return model;
    }

    @RequestMapping(path = "/helloWorld", method = RequestMethod.GET)
    public ModelAndView helloWorld() {
        ModelAndView model = new ModelAndView("HelloWorldPage");
        model.addObject("message", "hello world");

        return model;
    }

    @ResponseBody
    @RequestMapping("/lint/{name}")
    public String displayMessage(@PathVariable final String lint) {
        return lint;
    }

    @ResponseBody
    @RequestMapping("/displayDate/{date}")
    public String displayDate(@PathVariable @DateTimeFormat(iso = ISO.DATE) final Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
