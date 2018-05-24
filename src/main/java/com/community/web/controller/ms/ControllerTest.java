package com.community.web.controller.ms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vmtest")
public class ControllerTest {
    @GetMapping
    public ModelAndView list() {
        return new ModelAndView("echo", "messages", null);
    }
}
