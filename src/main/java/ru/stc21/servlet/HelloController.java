package ru.stc21.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.stc21.internal.HelloServer;
import ru.stc21.internal.User;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
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
    public String getHelloPageAndWriteToConsole(Model model) {
        String text = "Hello world";
        //model.addAttribute("text", text);
        return "hello";
    }

    @PostMapping("/hello")
    public String addUser(User user, Model model) throws ParseException {
        model.addAttribute("user", user);
        return "user";
    }
}
