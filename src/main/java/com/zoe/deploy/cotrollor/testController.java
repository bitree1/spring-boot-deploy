package com.zoe.deploy.cotrollor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {

    @RequestMapping
    public String index(){

        System.out.println("dddddMMMMMDDDD");
        return "index";
    }
}
