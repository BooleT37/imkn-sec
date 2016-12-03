package ru.naumen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.naumen.entities.Professor;
import ru.naumen.model.ProfessorDao;
import ru.naumen.model.StudentDao;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Objects;

/**
 * @author aarkaev
 */
@Controller
public class HomeController {

    @Inject
    private StudentDao storage;

    @Inject
	private ProfessorDao professorDao;

    
    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("allStudents", storage.findAll());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(HttpServletRequest request, Model model) {
    	String error = request.getParameter("error");
    	if (error != null && !error.isEmpty()) {
			switch (error) {
				case "passwordsDiffer":
					model.addAttribute("errorMessage", "Пароли отличаются");
					break;
				default: {
					model.addAttribute("errorMessage", error);
				}
			}
		}
    	return "signup";
    }

    @PostMapping("/signup")
	public void signup(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String login = request.getParameter("username");
		String password = request.getParameter("password");
		if (!Objects.equals(password, request.getParameter("repeatPassword"))) {
			response.sendRedirect("/signup?error=passwordsDiffer");
			return;
		}
    	Professor professor = new Professor();
    	professor.setLogin(login);
    	professor.setPassword(password);
		professorDao.save(professor);
		response.sendRedirect("/login?newUser=" + login);
	}



}
