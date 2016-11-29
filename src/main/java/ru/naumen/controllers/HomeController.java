package ru.naumen.controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.naumen.model.StudentDao;

/**
 * @author aarkaev
 */
@Controller
public class HomeController {

    @Inject
    private StudentDao storage;

    
    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("name", "Мир");
        model.addAttribute("allStudents", storage.findAll());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
