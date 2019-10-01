package com.agat.test.controller;

import com.agat.test.domain.Role;
import com.agat.test.domain.User;
import com.agat.test.repos.UserRepo;
import com.agat.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static javafx.scene.input.KeyCode.T;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private com.agat.test.repos.UserRepo UserRepo;

    @GetMapping("/registration")
    public String registration (){
        return "registration";
    }






//    @PostMapping("/registration")
//    public String addUser ( User user, Map<String, Object> model) {
//        User userFromDb = userRepo.findByUsername(user.getUsername());
//
//        // date
////        Calendar cal = Calendar.getInstance();
////        Date date=cal.getTime();
////        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
////        Date formattedDate=dateFormat.format(date);
//        //
//
//        if(userFromDb != null){
//            model.put("message", "User exists!");
//            return "registration";
//        }
//
//        user.setRoles(Collections.singleton(Role.USER));
//        user.setEntry_date(new Date(System.currentTimeMillis()));
//        user.setActive(true);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepo.save(user);
//
//        return "redirect:/login";
//    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        if (!userService.addUser(user)) {
            model.put("message", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);


        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
//            UserRepo.updateUser(code);
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }

        return "login";
    }


}
