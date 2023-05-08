package com.codeit.airports.managingairports.web;

import com.codeit.airports.managingairports.model.enumerations.Role;
import com.codeit.airports.managingairports.model.exceptions.InvalidArgumentsException;
import com.codeit.airports.managingairports.model.exceptions.InvalidUsernameOrPasswordException;
import com.codeit.airports.managingairports.model.exceptions.PasswordsDoNotMatchException;
import com.codeit.airports.managingairports.model.exceptions.UsernameAlreadyExistsException;
import com.codeit.airports.managingairports.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {


    private final UserService userService;

    public RegisterController(UserService userService) {

        this.userService = userService;
    }


    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        // model.addAttribute("bodyContent","register");
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam Role role
    ) {
        try {

            this.userService.register(username, password, repeatedPassword, role);
            return "redirect:/login?success=AccountSuccessfullyCreated";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException | UsernameAlreadyExistsException |
                 InvalidUsernameOrPasswordException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
