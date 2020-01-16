package com.malik.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.malik.account.Account;
import com.malik.database.Database;

@Controller
@RequestMapping("login.html")
public class LoginController {

    private Database database = Database.getInstance();

    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        return "frontPage/login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(HttpServletRequest request, ModelMap model) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || password == null || login.equals("") || password.equals("")) {
            model.addAttribute("error", "Empty login or password!");
            return "frontPage/login";
        }
        
        Account account = database.login(login, password);
        if (account == null) {
            model.addAttribute("error", "Wrong login or password!");
            return "frontPage/login";
        }

        model.addAttribute("account", account);
        return "index";
    }
}