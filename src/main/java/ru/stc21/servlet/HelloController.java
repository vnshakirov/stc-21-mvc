package ru.stc21.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.stc21.internal.HelloServer;

import javax.annotation.PostConstruct;

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

    @RequestMapping("/hello")
    public String getHelloPageAndWriteToConsole(Model model, @RequestParam("id") int id) {
        System.out.println(id);
        model.addAttribute("id", id);
        System.out.println(model);
        //System.out.println(server.getHelloString());
        return "index";
    }
}
