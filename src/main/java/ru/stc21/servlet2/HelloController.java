package ru.stc21.servlet2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        server.setHelloString("world");
    }

    @RequestMapping("/hello")
    public String getHelloPageAndWriteToConsole() {
        System.out.println(server.getHelloString());
        return "index";
    }
}
