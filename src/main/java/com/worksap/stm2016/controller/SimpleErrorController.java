package com.worksap.stm2016.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleErrorController {
    @RequestMapping(value = "/error/500")
    public String get500Error() {
        return "/error/500";
    }

    @RequestMapping(value = "/error/401")
    public String get401Error() {
        return "/error/401";
    }

    @RequestMapping(value = "/error/404")
    public String get404Error() {
        return "/error/404";
    }
}