package com.kubernetes.products.controller;

import com.kubernetes.products.model.MessageModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/home")
@RestController
public class HomeController {

    @GetMapping("")
    public MessageModel HomePage() {
        return new MessageModel("Home Page");
    }
}
