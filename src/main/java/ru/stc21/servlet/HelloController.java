package ru.stc21.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.stc21.internal.HelloServer;
import ru.stc21.internal.User;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
@SessionAttributes("user")
public class HelloController {

    private final HelloServer server;

    public HelloController(HelloServer server) {
        this.server = server;
    }

    @PostConstruct
    public void init() {
        server.setHelloString("Hello");
    }

    @GetMapping("/hello")
    public String getHelloPageAndWriteToConsole(User user, Model model) {
        String text = "Hello world";
        //model.addAttribute("text", text);
        return "hello";
    }

    @GetMapping("/hello2")
    public String getHelloPage(@ModelAttribute("user") User user, Model model) {
        return "redirect:/mvc/hello";
    }

    @PostMapping("/hello")
    public ModelAndView addUser(User user) throws ParseException {
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("userObject", user);
        //model.addAttribute("user", user);
        return mav;
    }
}
