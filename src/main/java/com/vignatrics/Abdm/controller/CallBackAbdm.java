package com.vignatrics.Abdm.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/cb")
public class CallBackAbdm {

    @GetMapping("/v3/58")
    public void v358(@RequestBody HttpServletRequest req){


    }
}
