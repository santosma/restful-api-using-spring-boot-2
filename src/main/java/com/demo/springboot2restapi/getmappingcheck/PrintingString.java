package com.demo.springboot2restapi.getmappingcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrintingString {
    @GetMapping("/testing2")
    public String printStringwithGetMapping(){
        return "RestController @GetMapping";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/testing1")
    public String printStringWithRequestMapping(){
        return "RestController @RequestMapping";
    }
}
