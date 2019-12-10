package ru.stc21.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.stc21.internal.ExtendedUser;
import ru.stc21.internal.HelloServer;
import ru.stc21.internal.User;
import ru.stc21.internal.UserCrudRepository;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ExtendedUser authenticatedUser = (ExtendedUser)authentication.getPrincipal();
        System.out.println(authenticatedUser.getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(authenticatedUser.getSnils());
        String text = "Hello world";
        return "hello";
    }

    @GetMapping("/hello2")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
