package com.agat.test.controller;

import com.agat.test.domain.Role;
import com.agat.test.domain.User;
import com.agat.test.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static javafx.scene.input.KeyCode.T;

@Controller
public class RegistrationController {
    @Autowired
    private com.agat.test.repos.UserRepo userRepo;
    @GetMapping("/registration")
    public String registration (){
        return "registration";
    }



    @PostMapping("/registration")
    public String addUser ( User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        // date
//        Calendar cal = Calendar.getInstance();
//        Date date=cal.getTime();
//        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//        Date formattedDate=dateFormat.format(date);
        //

        if(userFromDb != null){
            model.put("message", "User exists!");
            return "registration";
        }

        user.setRoles(Collections.singleton(Role.USER));
        user.setEntry_date(new Date(System.currentTimeMillis()));
        user.setActive(true);
        userRepo.save(user);

        return "redirect:/login";
    }
}
