package com.demo.springboot2restapi.getmappingcheck;

import com.demo.springboot2restapi.entities.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrintingStringController {
    @GetMapping("/testing2")
    public String printStringWithGetMapping() {
        return "RestController @GetMapping";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/testing1")
    public String printStringWithRequestMapping() {
        return "RestController @RequestMapping";
    }

    @GetMapping("/user-details")
    public UserDetails getUserDetails() {
        UserDetails dummyDetails = new UserDetails("Miguel", "Santos", "New York");
        System.out.println(dummyDetails);
        return dummyDetails;
    }
}
