package by.tms.springmarket66.controller;

import by.tms.springmarket66.dao.UserDao;
import by.tms.springmarket66.entity.User;
import by.tms.springmarket66.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderCreatorController {
    @Autowired
    @GetMapping // GET localhost:8080/test
    public String order() {
        return "order";
    }

    @PostMapping
    public String order(User user) {
        System.out.println(user);
        return "test";
    }
}
