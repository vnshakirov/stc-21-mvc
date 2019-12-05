package ru.stc21.servlet;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.stc21.internal.HelloServer;
import ru.stc21.internal.UserService;
import ru.stc21.model.User;

import javax.annotation.PostConstruct;

@Controller
@SessionAttributes("user")
public class HelloController {

    private final UserService service;

    public HelloController(UserService service) {
        this.service = service;
    }

    @PostConstruct
    public void init() {
        //server.setHelloString("Hello");
    }

    @RequestMapping("/hello")
    public ModelAndView getHelloPageAndWriteToConsole() {
        ModelAndView mav = new ModelAndView("hello");
        mav.addObject("login", "111");
        return mav;
    }

    @GetMapping("/index")
    public String getIndexPage(User user, Model model) {
        return "index";
    }

    @PostMapping("/hello")
    public String addUser(@RequestParam("login") String login, @RequestParam("password") String password) {
        service.addUser(login, password);
        return "index";
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addUser(User user, Model model) {
        service.addUser(user.getLogin(), user.getPassword());
        model.addAttribute("user", user);
        return "login";
    }

    @ExceptionHandler(RuntimeException.class)
    public String conflict(Exception e) {
        System.out.println("exception occured");
        e.printStackTrace();
        return "index";
    }
}
