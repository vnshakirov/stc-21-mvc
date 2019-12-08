package ru.stc21.servlet;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.stc21.internal.HelloServer;
import ru.stc21.internal.User;
import ru.stc21.internal.UserCrudRepository;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Stream;

@Controller
@SessionAttributes("user")
public class HelloController {

    private final HelloServer server;
    private final UserCrudRepository userCrudRepository;

    public HelloController(HelloServer server, UserCrudRepository userCrudRepository) {
        this.server = server;
        this.userCrudRepository = userCrudRepository;
    }

    @PostConstruct
    public void init() {
        server.setHelloString("Hello");
    }

    @GetMapping("/hello")
    public String getHelloPageAndWriteToConsole(User user, Model model) {
        String text = "Hello world";
        userCrudRepository.findAll();
        userCrudRepository.updateLogin("123", "345");

        User user1 = new User();
        user1.setPassword("123");
        List<User> users = userCrudRepository.findAll(Example.of(user1));
        return "hello";
    }

    @GetMapping("/hello2")
    public String getHelloPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("userObject", user);
        return "user";
    }

    @PostMapping("/hello")
    public ModelAndView addUser(User user) throws ParseException {
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("userObject", user);
        //model.addAttribute("user", user);
        return mav;
    }
}
