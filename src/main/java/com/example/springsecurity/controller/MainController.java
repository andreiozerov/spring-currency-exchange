package com.example.springsecurity.controller;

import com.example.springsecurity.dto.UserDto;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.exception.UserAlreadyExistException;
import com.example.springsecurity.service.SecurityUserService;
import com.example.springsecurity.service.UserServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class MainController {

    private SecurityUserService securityUserService;
    private UserServiceDto userServiceDto;

    public MainController(UserServiceDto userServiceDto) {
        this.userServiceDto = userServiceDto;
    }

    @Autowired
    public void setUserService(SecurityUserService securityUserService) {
        this.securityUserService = securityUserService;
    }

    @GetMapping("/")
    public String home() {
        return "/login";
    }
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/currency")
    public String currency() {
        return "/currency";
    }

    @GetMapping("/error")
    public String error() {
        return "/error";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, HttpServletRequest request, Errors errors) {
        try {
            User registered = userServiceDto.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException ex) {
            ModelAndView mav = new ModelAndView("registration", "user", userDto);
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }

        return new ModelAndView("successRegister", "user", userDto);
    }
}
